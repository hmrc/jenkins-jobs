package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new BuildMonitorViewBuilder('TAI-MONITOR')
        .withJobs('tai-frontend').build(this)

new SbtFrontendJobBuilder('tai-frontend').
        withTests("test").
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)
