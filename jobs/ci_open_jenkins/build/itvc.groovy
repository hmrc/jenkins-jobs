package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtFrontendJobBuilder('income-tax-view-change-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('manage-income-tax-subscription-frontend').
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
        'manage-income-tax-subscription-frontend',
        'income-tax-view-change',
        'income-tax-view-change-dynamic-stub',
        'financial-transactions'
).build(this)
