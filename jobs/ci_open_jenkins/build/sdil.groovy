package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

def frontends = ["soft-drinks-industry-levy-frontend"]
def liabiltyFrontend = ["soft-drinks-industry-levy-liability-tool-frontend"]

new SbtFrontendJobBuilder('soft-drinks-industry-levy-liability-tool-frontend')
        .withSCoverage()
        .build(this as DslFactory)

def services = ["soft-drinks-industry-levy",
                "soft-drinks-industry-levy-stub",
                "soft-drinks-industry-levy-liability-calculator"]

def allServices = services + frontends + liabilityFrontend

frontends.each {
    new SbtFrontendJobBuilder(it).withTests("test").build(this as DslFactory)
}

services.each {
    new SbtMicroserviceJobBuilder(it)
            .withTests("test")
            .build(this as DslFactory)
}

new BuildMonitorViewBuilder('SDIL-MONITOR').withJobs(*allServices).build(this)
