package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('automation-tests').
        build(this as DslFactory)

new BuildMonitorViewBuilder('AUTOMATION-TESTS-MONITOR')
        .withJobs('automation-tests').build(this)

