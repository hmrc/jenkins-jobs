package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.BooleanParameter.booleanParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.ChoiceParameter.choiceParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.NodeParameter.nodeParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.StringParameter.stringParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.BuildDescriptionPublisher.buildDescriptionByRegexPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep
import static uk.gov.hmrc.jenkinsjobbuilders.domain.trigger.CronTrigger.cronTrigger
import static uk.gov.hmrc.jenkinsjobbuilders.domain.trigger.PollTrigger.pollTrigger
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variable.StringEnvironmentVariable.stringEnvironmentVariable
import static uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.SecretTextCredentialsWrapper.secretTextCredentials
import static uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.model.SecretText.secretText
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.defaultBuildDescriptionPublisher
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.cleanPublishSigned
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.createARelease
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.createAReleaseWithScalaVersion
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.createARepository
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.createAWebhook

new SbtLibraryJobBuilder('sbt-git-versioning').
        withoutJUnitReports().
        build(this)

new SbtLibraryJobBuilder('time').
        build(this)

new SbtLibraryJobBuilder('sbt-bobby').
        build(this)

new SbtLibraryJobBuilder('zap-automation').
        build(this)

new SbtLibraryJobBuilder('accessibility-testing-library').
        withSCoverage().
        build(this as DslFactory)

new SbtLibraryJobBuilder('git-stamp').
        withoutJUnitReports().
        build(this)

new SbtLibraryJobBuilder('init-repository').
        build(this)


jobBuilder('init-service', 'init-service', 'master')
        .withTriggers(cronTrigger('H 00 * * *'), pollTrigger('H/6 * * * *'))
        .withSteps(shellStep("""|
                                |mkdir -p \${WORKSPACE}/tmp
                                |python scripts/test/tests.py
                                |python scripts/test/integration-tests.py
                               """.stripMargin())).build(this)


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

new SbtLibraryJobBuilder('sbt-artifactory').
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
        withSteps(cleanPublishSigned()).
        withPublishers(
                defaultBuildDescriptionPublisher()).
        build(this)

jobBuilder('create-a-repository').
        withEnvironmentVariables(stringEnvironmentVariable('INIT_REPO_VERSION', '0.35.0')).
        withEnvironmentVariables(stringEnvironmentVariable('INIT_WEBHOOK_VERSION', '0.14.0')).
        withEnvironmentVariables(stringEnvironmentVariable('CRED_FILE_PATH', '/var/lib/jenkins/.github/.credentials')).
        withParameters(stringParameter('REPOSITORY_NAME', '', 'The repository name e.g. foo-frontend')).
        withParameters(stringParameter('TEAM_NAME', '', 'The exact name of the github team to which the repository will be added')).
        withParameters(choiceParameter('REPOSITORY_TYPE', ['Sbt', 'SbtPlugin'], 'The repository type e.g. SBT')).
        withParameters(stringParameter('BOOTSTRAP_TAG', '0.1.0', 'The bootstrap tag to kickstart release candidates. This should be 0.1.0 for *new* repositories or the most recent internal tag version for *migrated* repositories')).
        withParameters(stringParameter('DIGITAL_SERVICE_NAME', "",'The digital service name that this repository belongs to (Optional)')).
        withWrappers(
                secretTextCredentials(
                    secretText('LDS_WEBHOOK_SECRET', 'leak-detection-service-webhook-secret'),
                    secretText('LDS_URL', 'leak-detection-service-url')
                )
        ).
        withSteps(createARepository('$REPOSITORY_NAME', '$TEAM_NAME', '$REPOSITORY_TYPE', '$BOOTSTRAP_TAG', '$ENABLE_TRAVIS', '$DIGITAL_SERVICE_NAME')).
        withSteps(createAWebhook('$CRED_FILE_PATH', 'https://api.github.com', 'hmrc', '$REPOSITORY_NAME', '$LDS_URL/validate', 'push', 'application/json', '$LDS_WEBHOOK_SECRET')).
        withPublishers(buildDescriptionByRegexPublisher('\\[INFO\\] Github repositories and Bintray packages successfully created (.*)')).
        build(this)


jobBuilder('create-a-webhook')
        .withEnvironmentVariables(stringEnvironmentVariable('INIT_WEBHOOK_VERSION', '0.14.0'))
        .withParameters(stringParameter('CRED_FILE_PATH', '/var/lib/jenkins/.github/.credentials', 'path of file containing git credentials'))
        .withParameters(stringParameter('API_BASE_URL', 'https://api.github.com', 'base url for git dev api'))
        .withParameters(stringParameter('ORG', 'hmrc', 'repository organization'))
        .withParameters(stringParameter('REPOSITORY_NAMES', '', 'comma separated list of repository names e.g. foo-frontend,foo-service'))
        .withParameters(stringParameter('WEBHOOK_URL', '', 'url for the notification'))
        .withParameters(stringParameter('EVENTS', 'issues,issue_comment,pull_request,pull_request_review_comment', 'comma separated list of git events to be notified e.g issues,pull_request if not specified defaults will be used'))
        .withParameters(choiceParameter('CONTENT_TYPE', ['application/json', 'application/x-www-form-urlencoded'], 'The format of the body sent to the Webhook'))
        .withSteps(createAWebhook('$CRED_FILE_PATH', '$API_BASE_URL', '$ORG', '$REPOSITORY_NAMES', '$WEBHOOK_URL', '$EVENTS', '$CONTENT_TYPE'))
        .build(this)


jobBuilder('create-a-release').
        withEnvironmentVariables(stringEnvironmentVariable('RELEASER_VERSION', '1.7.0')).
        withParameters(stringParameter('ARTEFACT_NAME', '', 'The artifact name e.g. cato-frontend'),
                stringParameter('RELEASE_CANDIDATE_VERSION', '', 'The release candidate e.g. 1.3.0-1-g21312cc'),
                choiceParameter('RELEASE_TYPE', ['MINOR', 'MAJOR', 'HOTFIX'], 'The type of release e.g. MINOR'),
                stringParameter('RELEASE_NOTES', '', 'Optional release notes addendum, should describe briefly a reason for change.')).
        withSteps(createARelease()).
        withPublishers(buildDescriptionByRegexPublisher('\\[INFO\\] Releaser successfully released (.*)')).
        build(this)

jobBuilder('create-a-2.12-release').
        withEnvironmentVariables(stringEnvironmentVariable('RELEASER_VERSION', '1.5.0')).
        withParameters(stringParameter('ARTEFACT_NAME', '', 'The artifact name e.g. cato-frontend'),
                stringParameter('RELEASE_CANDIDATE_VERSION', '', 'The release candidate e.g. 1.3.0-1-g21312cc'),
                choiceParameter('RELEASE_TYPE', ['MINOR', 'MAJOR', 'HOTFIX'], 'The type of release e.g. MINOR')).
        withSteps(createAReleaseWithScalaVersion("2.12")).
        withPublishers(buildDescriptionByRegexPublisher('\\[INFO\\] Releaser successfully released (.*)')).
        build(this)

jobBuilder('clean-slaves').
        withParameters(nodeParameter('slaves', (1..4).collect {
            "ci-open-slave-$it"
        }, 'allowMultiSelectionForConcurrentBuilds')).
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


new SbtMicroserviceJobBuilder('teams-and-repositories').withTests("test")
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('catalogue-frontend').withTests("test")
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('leak-detection').withTests("test")
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('slack-notifications').withTests("test")
        .build(this as DslFactory)

new SbtLibraryJobBuilder('alert-config-builder').build(this as DslFactory)

new SbtMicroserviceJobBuilder('indicators').withTests("test")
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('service-deployments').withTests("test")
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('repository-jobs').withTests("test")
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('service-dependencies').withTests("test")
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('library-upgrade-progress-frontend').withTests("test")
        .build(this as DslFactory)

new SbtLibraryJobBuilder('github-client').build(this as DslFactory)

new SbtLibraryJobBuilder('git-client').build(this as DslFactory)

new SbtLibraryJobBuilder('init-prototype').build(this as DslFactory)

new SbtLibraryJobBuilder('http-core').build(this as DslFactory)

new SbtLibraryJobBuilder('http-verbs-play-25').build(this as DslFactory)

new SbtLibraryJobBuilder('bootstrap-play-25').build(this as DslFactory)

new BuildMonitorViewBuilder('PLATOPS-MONITOR')
        .withJobs('sbt-git-versioning', 'time', 'sbt-bobby', 'jenkins-job-builders', 'git-stamp', 'init-repository', 'releaser', 'govuk-template', 'sbt-bintray-publish', 'sbt-auto-build', 'sbt-git-stamp', 'sbt-settings', 'sbt-distributables', 'teams-and-services', 'catalogue-frontend', 'alert-config-builder', 'init-service', 'indicators', 'service-deployments', 'create-a-release', 'create-a-repository', 'create-a-webhook', 'github-client', 'library-upgrade-progress-frontend').build(this)

jobBuilder('create-a-service', 'init-service')                                            
        .withEnvironmentVariables(stringEnvironmentVariable('INIT_REPO_VERSION', '0.35.0'))
        .withEnvironmentVariables(stringEnvironmentVariable('INIT_WEBHOOK_VERSION', '0.14.0'))
        .withEnvironmentVariables(stringEnvironmentVariable('CRED_FILE_PATH', '/var/lib/jenkins/.github/.credentials'))
        .withLabel('master')
        .withParameters(stringParameter('REPOSITORY_NAME', '', 'The repository name e.g. foo-frontend.'))
        .withParameters(stringParameter('TEAM_NAME', '', 'The exact name of the team as in: catalogue'))
        .withParameters(choiceParameter('TYPE', ['MICROSERVICE', 'FRONTEND'], 'Type of the service'))
        .withParameters(stringParameter('BOOTSTRAP_TAG', '0.1.0', 'The bootstrap tag to kickstart release candidates. This should be 0.1.0 for *new* services or the most recent internal tag version for *migrated* services'))
        .withParameters(booleanParameter('WITH_MONGO', false, 'Selecting this options will include the mongo connector library and plugin as project dependencies.'))
        .withParameters(stringParameter('DIGITAL_SERVICE_NAME', "",'The digital service name that this repository belongs to (Optional)'))
        .withWrappers(
                secretTextCredentials(
                        secretText('LDS_WEBHOOK_SECRET', 'leak-detection-service-webhook-secret'),
                        secretText('LDS_URL', 'leak-detection-service-url')
                )
        )
        .withSteps(createARepository('$REPOSITORY_NAME', '$TEAM_NAME', 'Sbt', '0.1.0', '', '$DIGITAL_SERVICE_NAME'))
        .withSteps(shellStep("""|
                                |withMongo=""
                                |
                                |if [ "\$WITH_MONGO" = true ]; then
                                |  withMongo="-use_mongo"
                                |fi
                                |
                                |python scripts/bin/create.py \${REPOSITORY_NAME} \${TYPE} -exists \$withMongo
                               """.stripMargin()))
        .withSteps(createAWebhook('$CRED_FILE_PATH', 'https://api.github.com', 'hmrc', '$REPOSITORY_NAME', '$LDS_URL/validate', 'push', 'application/json', '$LDS_WEBHOOK_SECRET'))
        .withPublishers(buildDescriptionByRegexPublisher('\\[INFO\\] Successfully created https://github.com/hmrc/(.*)'))
        .build(this)
