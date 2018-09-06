import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

def frontends = ["self-service-time-to-pay-frontend"]

def services = ["self-service-time-to-pay",
                    "self-service-time-to-pay-des-stub",
                    "self-service-time-to-pay-des-stub-scala",
                    "time-to-pay-arrangement",
                    "time-to-pay-calculator",
                    "time-to-pay-eligibility",
                    "time-to-pay-taxpayer",
                    "ia-frontend",
                    "ia",
                    "sa-stub"]

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