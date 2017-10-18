import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

//This file defines only builds for open repos.
//In order to define builds for other repos edit similar project named 'jenkins-config'

def frontends = [
    "self-service-time-to-pay-frontend"
]

def services = [
    "time-to-pay-calculator",
    "time-to-pay-eligibility",
    "time-to-pay-taxpayer",
    "time-to-pay-arrangement",
    "self-service-time-to-pay-des-stub-scala",
    "sa-stub"
]


def allServices = frontends + services

frontends.each {
    new SbtFrontendJobBuilder(it).build(this as DslFactory)
}

services.each {
    new SbtMicroserviceJobBuilder(it)
            .withScalaStyle()
            .withSCoverage()
            .withTests("test it:test")
            .build(this as DslFactory)
}

new BuildMonitorViewBuilder('SSTTP-MONITOR').withJobs(*allServices).build(this)