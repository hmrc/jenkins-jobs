package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
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
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.defaultBuildDescriptionPublisher
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.*

new SbtLibraryJobBuilder('sbt-bobby').
        build(this)

new SbtLibraryJobBuilder('service-integration-test').
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('releaser').
        build(this)

new SbtLibraryJobBuilder('govuk-template').
        withoutJUnitReports().
        build(this)

new SbtLibraryJobBuilder('sbt-bintray-publish').
        withoutJUnitReports().
        build(this)

new SbtLibraryJobBuilder('sbt-artifactory').
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

new SbtMicroserviceJobBuilder('library-upgrade-progress-frontend').withTests("test")
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('sample-reactivemongo').withTests("test")
        .build(this as DslFactory)

new SbtLibraryJobBuilder('init-prototype').build(this as DslFactory)