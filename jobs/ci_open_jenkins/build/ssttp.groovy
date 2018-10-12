import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

def services = [
        "ia-frontend",
       // "self-service-time-to-pay",
        "time-to-pay-arrangement",
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