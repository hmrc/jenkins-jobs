import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

def services = [
        "self-service-time-to-pay-frontend",
        "ia-frontend",
        "self-service-time-to-pay",
        "self-service-time-to-pay-des-stub",
        "self-service-time-to-pay-des-stub-scala",
        "time-to-pay-arrangement",
        "time-to-pay-calculator",
        "time-to-pay-eligibility",
        "time-to-pay-taxpayer",
        "ia",
        "sa-stub"]

def allServices = services

services.each {
    new SbtMicroserviceJobBuilder(it)
            .withScalaStyle()
            .withSCoverage()
            .withTests("test it:test")
            .build(this as DslFactory)
}

new BuildMonitorViewBuilder('SSTTP-MONITOR').withJobs(*allServices).build(this)