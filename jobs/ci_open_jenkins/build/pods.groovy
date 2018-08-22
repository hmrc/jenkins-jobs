package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('pension-administrator').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)


new BuildMonitorViewBuilder('PODS-TEMP-MONITOR')
        .withJobs('pension-administrator'
).build(this)
