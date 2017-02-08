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

def frontends = ["off-payroll-frontend","pdf-generator-frontend"]

def services = ["pdf-generator-service","off-payroll-decision"]

def allServices = services + frontends

frontends.each {
    new SbtFrontendJobBuilder(it)
        .withTests("test")
        .build(this as DslFactory)
}

services.each {
    new SbtMicroserviceJobBuilder(it)
        	.withTests("test")
            .build(this as DslFactory)
}

new SbtFrontendJobBuilder("off-payroll-frontend-integration","off-payroll-frontend","integration")
        .withTests("test")
        .build(this as DslFactory)


new BuildMonitorViewBuilder('OFF-PAYROLL-OPEN-MONITOR').withJobs(*allServices, "off-payroll-frontend-integration").build(this)