package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.ChoiceParameter.choiceParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.StringParameter.stringParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.BuildDescriptionPublisher.buildDescriptionByRegexPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variable.StringEnvironmentVariable.stringEnvironmentVariable
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.createAJavaRelease

def javaLibraries = ["http-verbs-java",
                     "play-filters-java",
                     "frontend-bootstrap-java",
                     "microservice-bootstrap-java",
                     "java-releaser-poc"]

def frontends = ["eeitt-frontend", "eeitt-admin-frontend", "eeitt-business-console"]

def services = ["eeitt", "eeitt-dashboard", "eeitt-admin"]

def allServices = services + frontends

frontends.each {
    new SbtFrontendJobBuilder(it).build(this as DslFactory)
}

services.each {
    new SbtMicroserviceJobBuilder(it)
            .withTests("test it:test")
            .build(this as DslFactory)
}

javaLibraries.each {
    new SbtLibraryJobBuilder(it).build(this as DslFactory)
}

jobBuilder('create-a-java-release').
        withEnvironmentVariables(stringEnvironmentVariable('RELEASER_VERSION', '0.3.0')).
        withParameters(stringParameter('ARTEFACT_NAME', '', 'The artifact name e.g. cato-frontend'),
                stringParameter('RELEASE_CANDIDATE_VERSION', '', 'The release candidate e.g. 1.3.0-1-g21312cc'),
                choiceParameter('RELEASE_TYPE', ['MINOR', 'MAJOR', 'HOTFIX'], 'The type of release e.g. MINOR')).
        withSteps(createAJavaRelease()).
        withPublishers(buildDescriptionByRegexPublisher('\\[INFO\\] Java Releaser successfully released (.*)')).
        build(this)

new BuildMonitorViewBuilder('EEITT-MONITOR').withJobs(*allServices).build(this)
