package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtFrontendJobBuilder('income-tax-view-change-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('income-tax-view-change').
        withScalaStyle().
        withSCoverage().
		build(this as DslFactory)

new SbtMicroserviceJobBuilder('income-tax-view-change-dynamic-stub').
        withScalaStyle().
        withSCoverage().
    build(this as DslFactory)

new BuildMonitorViewBuilder('ITVC-MONITOR').withJobs(
        'income-tax-view-change-frontend',
        'income-tax-view-change',
        'income-tax-view-change-dynamic-stub'
).build(this)
