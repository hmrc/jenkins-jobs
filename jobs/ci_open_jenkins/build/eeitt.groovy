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
                     "microservice-bootstrap-java"]

def frontends = ["eeitt-frontend", "eeitt-admin-frontend", "eeitt-business-console"]

def services = []

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

new BuildMonitorViewBuilder('EEITT-MONITOR').withJobs(*allServices).build(this)
