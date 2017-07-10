package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

def services = ["pensions-lifetime-allowance-des-stub"]

new SbtMicroserviceJobBuilder('pensions-lifetime-allowance-des-stub')
        .withTests("test")
        .build(this as DslFactory)

new BuildMonitorViewBuilder('PSAL-MONITOR').withJobs(*services).build(this)
