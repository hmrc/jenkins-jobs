package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.GradleLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.ChoiceParameter.choiceParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.NodeParameter.nodeParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.StringParameter.stringParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.BuildDescriptionPublisher.buildDescriptionByRegexPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep
import static uk.gov.hmrc.jenkinsjobbuilders.domain.trigger.CronTrigger.cronTrigger
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variable.StringEnvironmentVariable.stringEnvironmentVariable
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.*
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.*

new SbtLibraryJobBuilder('sbt-git-versioning').
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('time').
                         build(this)

new SbtLibraryJobBuilder('sbt-bobby').
                         build(this)

new GradleLibraryJobBuilder('jenkins-job-builders').
                            build(this)

new SbtLibraryJobBuilder('git-stamp').
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('init-repository').
                        build(this)

jobBuilder('init-service', 'init-service', 'master').
        withTriggers(cronTrigger('H H/1 * * *')).
        withSteps(sbtCleanTestPublish()).
        withPublishers(defaultHtmlReportsPublisher(),
                bobbyArtifactsPublisher(),
                defaultBuildDescriptionPublisher())

new SbtLibraryJobBuilder('init-webhook').build(this)


new SbtLibraryJobBuilder('releaser').
                         build(this)

new SbtLibraryJobBuilder('govuk-template').
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('sbt-bintray-publish').
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('sbt-auto-build').
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('sbt-git-stamp').
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('sbt-settings').
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('sbt-distributables').
                         withoutJUnitReports().
                         build(this)

jobBuilder("ReactiveMongo-HMRC-Fork", "ReactiveMongo", "socket-timeout-backport-to-0-11-5").
        withSteps(cleanPublishSigned()).
        withPublishers(
                defaultBuildDescriptionPublisher()).
        build(this)

jobBuilder('create-a-repository').
          withEnvironmentVariables(stringEnvironmentVariable('INIT_REPO_VERSION', '0.17.4')).
          withParameters(stringParameter('REPOSITORY_NAME','','The repository name e.g. foo-frontend')).
          withParameters(stringParameter('TEAM_NAME','','The exact name of the github team to which the repository will be added')).
          withParameters(choiceParameter('REPOSITORY_TYPE',['Sbt','SbtPlugin'],'The repository type e.g. SBT')).
          withSteps(createARepository('$REPOSITORY_NAME', '$TEAM_NAME', '$REPOSITORY_TYPE')).
          withPublishers(buildDescriptionByRegexPublisher('\\[INFO\\] Github repositories and Bintray packages successfully created (.*)')).
          build(this)


jobBuilder('create-a-webhook')
        .withEnvironmentVariables(stringEnvironmentVariable('INIT_WEBHOOK_VERSION', '0.7.0'))
        .withParameters(stringParameter('CRED_FILE_PATH', '/var/lib/jenkins/.github/.credentials', 'path of file containing git credentials'))
        .withParameters(stringParameter('API_BASE_URL', 'https://api.github.com', 'base url for git dev api'))
        .withParameters(stringParameter('ORG', 'hmrc', 'repository organization'))
        .withParameters(stringParameter('REPOSITORY_NAMES', '', 'comma separated list of repository names e.g. foo-frontend,foo-service'))
        .withParameters(stringParameter('WEBHOOK_URL', '', 'url for the notification'))
        .withParameters(stringParameter('EVENTS', 'issues,issue_comment,pull_request,pull_request_review_comment','comma separated list of git events to be notified e.g issues,pull_request if not specified defaults will be used'))
        .withSteps(createAWebhook('$CRED_FILE_PATH', '$API_BASE_URL', '$ORG', '$REPOSITORY_NAMES', '$WEBHOOK_URL', '$EVENTS'))
        .build(this)


jobBuilder('create-a-release').
           withEnvironmentVariables(stringEnvironmentVariable('RELEASER_VERSION', '0.12.0')).
           withParameters(stringParameter('ARTEFACT_NAME','','The artifact name e.g. cato-frontend'),
                          stringParameter('RELEASE_CANDIDATE_VERSION', '', 'The release candidate e.g. 1.3.0-1-g21312cc'),
                          choiceParameter('RELEASE_TYPE', ['MINOR', 'MAJOR', 'HOTFIX'], 'The type of release e.g. MINOR')).
           withSteps(createARelease()).
           withPublishers(buildDescriptionByRegexPublisher('\\[INFO\\] Releaser successfully released (.*)')).
           build(this)

jobBuilder('clean-slaves').
           withParameters(nodeParameter('slaves', (1..4).collect {"ci-open-slave-$it"}, 'allowMultiSelectionForConcurrentBuilds')).
           withConcurrentBuilds().
           withLabel('master').
           withTriggers(cronTrigger('H 23 * * 4')).
           withSteps(shellStep("""\
                               |rm -rf ~/.m2/repository
                               |rm -rf ~/.ivy2
                               |find ~/workspace -maxdepth 1 -type d -mtime +30 -print0 | xargs -0 rm -rf
                               |find /var/tmp -maxdepth 1 -type d -name "*_LOCAL" -user "jenkins" -print0 | xargs -0 rm -rf
                               """.stripMargin())).
           build(this)


new SbtMicroserviceJobBuilder('teams-and-services').withTests("test")
       .build(this as DslFactory)

new SbtMicroserviceJobBuilder('catalogue-frontend').withTests("test")
        .build(this as DslFactory)

new SbtLibraryJobBuilder('alert-config-builder').build(this as DslFactory)

new SbtMicroserviceJobBuilder('indicators').withTests("test")
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('service-releases').withTests("test")
        .build(this as DslFactory)


new SbtLibraryJobBuilder('github-client').build(this as DslFactory)

new SbtLibraryJobBuilder('git-client').build(this as DslFactory)

new BuildMonitorViewBuilder('PLATOPS-MONITOR')
        .withJobs('sbt-git-versioning', 'time', 'sbt-bobby', 'jenkins-job-builders', 'git-stamp', 'init-repository', 'releaser', 'govuk-template', 'sbt-bintray-publish', 'sbt-auto-build', 'sbt-git-stamp', 'sbt-settings', 'sbt-distributables', 'teams-and-services', 'catalogue-frontend', 'alert-config-builder', 'init-service', 'indicators', 'service-releases', 'create-a-release', 'create-a-repository', 'create-a-webhook', 'github-client').build(this)
