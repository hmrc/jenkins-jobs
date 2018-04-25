package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new BuildMonitorViewBuilder('TAI-MONITOR')
        .withJobs('tai-frontend', 'tai').build(this)

new SbtMicroserviceJobBuilder('tai').
        withScalaStyle().
        withSCoverage().
        withTests("test").
        build(this as DslFactory)

new SbtFrontendJobBuilder('tai-frontend').
        withTests("test").
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)
