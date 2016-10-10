package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.GradleMicroserviceJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.ChoiceParameter.choiceParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.StringParameter.stringParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.StringParameter.stringParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.BuildDescriptionPublisher.buildDescriptionByRegexPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variable.StringEnvironmentVariable.stringEnvironmentVariable
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.createAJavaRelease
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.createARelease

new SbtMicroserviceJobBuilder('zone-health').build(this as DslFactory)

new SbtMicroserviceJobBuilder('self-service-time-to-pay').build(this as DslFactory)

new SbtFrontendJobBuilder('self-service-time-to-pay-frontend').build(this as DslFactory)

new SbtLibraryJobBuilder('http-verbs-java').build(this as DslFactory)

new SbtLibraryJobBuilder('play-filters-java').build(this as DslFactory)

new SbtLibraryJobBuilder('frontend-bootstrap-java').build(this as DslFactory)

new SbtLibraryJobBuilder('microservice-bootstrap-java').build(this as DslFactory)

new SbtLibraryJobBuilder('java-releaser-poc').build(this)

new GradleMicroserviceJobBuilder('time-to-pay-arrangement').build(this as DslFactory)



jobBuilder('create-a-java-release').
        withEnvironmentVariables(stringEnvironmentVariable('RELEASER_VERSION', '0.3.0')).
        withParameters(stringParameter('ARTEFACT_NAME','','The artifact name e.g. cato-frontend'),
                stringParameter('RELEASE_CANDIDATE_VERSION', '', 'The release candidate e.g. 1.3.0-1-g21312cc'),
                choiceParameter('RELEASE_TYPE', ['MINOR', 'MAJOR', 'HOTFIX'], 'The type of release e.g. MINOR')).
        withSteps(createAJavaRelease()).
        withPublishers(buildDescriptionByRegexPublisher('\\[INFO\\] Java Releaser successfully released (.*)')).
        build(this)


new BuildMonitorViewBuilder('SSTTP-MONITOR')
        .withJobs('self-service-time-to-pay','self-service-time-to-pay-frontend', 'time-to-pay-arrangement').build(this)
