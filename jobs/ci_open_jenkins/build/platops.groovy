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
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.SbtStep.sbtStep
import static uk.gov.hmrc.jenkinsjobbuilders.domain.trigger.CronTrigger.cronTrigger
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variable.StringEnvironmentVariable.stringEnvironmentVariable
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.defaultBuildDescriptionPublisher
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.createARelease
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.createARepository

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

jobBuilder("ReactiveMongo-HMRC-Fork", "ReactiveMongo").
        withSteps(sbtStep("clean 'test-only -- exclude mongo2' publishSigned")).
        withPublishers(
                defaultBuildDescriptionPublisher()).
        build(this)

jobBuilder('create-a-repository').
          withEnvironmentVariables(stringEnvironmentVariable('INIT_REPO_VERSION', '0.13.0')).
          withParameters(stringParameter('REPOSITORY_NAME','','The repository name e.g. foo-frontend')).
          withParameters(stringParameter('TEAM_NAME','','The exact name of the github team to which the repository will be added')).          
          withParameters(choiceParameter('REPOSITORY_TYPE',['Sbt','SbtPlugin'],'The repository type e.g. SBT')).
          withSteps(createARepository('$REPOSITORY_NAME', '$TEAM_NAME', '$REPOSITORY_TYPE')).
          withPublishers(buildDescriptionByRegexPublisher('\\[INFO\\] Github repositories and Bintray packages successfully created (.*)')).
          build(this)

jobBuilder('create-a-release').
           withEnvironmentVariables(stringEnvironmentVariable('RELEASER_VERSION', '0.11.0')).
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
                               |rm -rf ~/.m2
                               |rm -rf ~/.ivy2
                               |find ~/workspace -maxdepth 1 -type d -mtime +30 -print0 | xargs -0 rm -rf
                               |find /var/tmp -maxdepth 1 -type d -name "*_LOCAL" -user "jenkins" -print0 | xargs -0 rm -rf
                               """.stripMargin())).
           build(this)

new SbtMicroserviceJobBuilder('catalogue').withTests("test")
        .build(this as DslFactory)

new SbtLibraryJobBuilder('alert-config-builder').build(this as DslFactory)


new BuildMonitorViewBuilder('PLATOPS-MONITOR')
        .withJobs('sbt-git-versioning', 'time', 'sbt-bobby', 'jenkins-job-builders', 'git-stamp', 'init-repository', 'releaser', 'govuk-template', 'sbt-bintray-publish', 'sbt-auto-build', 'sbt-git-stamp', 'sbt-settings', 'sbt-distributables', 'catalogue', 'alert-config-builder', 'init-service').build(this)
