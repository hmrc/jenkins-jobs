package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

def frontends = ["soft-drinks-industry-levy-frontend"]

def services = ["soft-drinks-industry-levy",
                "soft-drinks-industry-levy-stub"]

def allServices = services + frontends

frontends.each {
    new SbtFrontendJobBuilder(it).build(this as DslFactory)
}

services.each {
    new SbtMicroserviceJobBuilder(it)
            .withTests("test it:test")
            .build(this as DslFactory)
}

new BuildMonitorViewBuilder('SDIL-MONITOR').withJobs(*allServices).build(this)
