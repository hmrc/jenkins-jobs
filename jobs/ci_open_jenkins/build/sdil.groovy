package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

def frontends = ["soft-drinks-industry-levy-frontend",
                 "soft-drinks-industry-levy-liability-tool-frontend"]

def services = ["soft-drinks-industry-levy",
                "soft-drinks-industry-levy-stub",
                "soft-drinks-industry-levy-liability-calculator"]

def allServices = services + frontends

frontends.each {
    new SbtFrontendJobBuilder(it).withTests("test").withSCoverage().build(this as DslFactory)
}

services.each {
    new SbtMicroserviceJobBuilder(it)
            .withTests("test")
            .build(this as DslFactory)
}

new BuildMonitorViewBuilder('SDIL-MONITOR').withJobs(*allServices).build(this)
