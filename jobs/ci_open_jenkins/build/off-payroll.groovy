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

def frontends = ["off-payroll-frontend","pdf-generator-frontend","off-payroll-analytics-frontend"]

def services = ["pdf-generator-service","off-payroll-decision"]

def allServices = services + frontends

frontends.each {
    new SbtFrontendJobBuilder(it)
        .withTests("test")
        .withLogRotator(7, 1000)
        .build(this as DslFactory)
}

services.each {
    new SbtMicroserviceJobBuilder(it)
        	.withTests("test")
            .withLogRotator(7, 1000)
            .build(this as DslFactory)
}

new SbtFrontendJobBuilder("off-payroll-frontend-develop","off-payroll-frontend","develop")
        .withTests("test")
        .withLogRotator(7, 1000)
        .build(this as DslFactory)

new SbtFrontendJobBuilder("off-payroll-decision-develop","off-payroll-decision","develop")
        .withTests("test")
        .withLogRotator(7, 1000)
        .build(this as DslFactory)

new SbtFrontendJobBuilder("pdf-generator-service-develop","pdf-generator-service","develop")
        .withTests("test")
        .withLogRotator(7, 1000)
        .build(this as DslFactory)

new SbtFrontendJobBuilder("pdf-generator-frontend-develop","pdf-generator-frontend","develop")
        .withTests("test")
        .withLogRotator(7, 1000)
        .build(this as DslFactory)

new SbtFrontendJobBuilder("off-payroll-analytics-frontend-develop","off-payroll-analytics-frontend","develop")
        .withTests("test")
        .withLogRotator(7, 1000)
        .build(this as DslFactory)



new BuildMonitorViewBuilder('OFF-PAYROLL-OPEN-MONITOR').withJobs(*allServices, "off-payroll-frontend-develop",
        "off-payroll-decision-develop", "pdf-generator-service-develop", "off-payroll-analytics-frontend-develop").build(this)

