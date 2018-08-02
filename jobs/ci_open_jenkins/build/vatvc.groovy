package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtFrontendJobBuilder('manage-vat-subscription-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('view-vat-returns-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('deregister-vat-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('vat-summary-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('vat-agent-client-lookup-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)


new SbtMicroserviceJobBuilder('vat-vc-api-stub').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('vat-obligations').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('vat-returns').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('vat-subscription').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('manage-vat-subscription-dynamic-stub').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('vat-obligations-dynamic-stub').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('vat-returns-dynamic-stub').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('VATVC-MONITOR').withJobs(
        'manage-vat-subscription-frontend',
        'view-vat-returns-frontend',
        'deregister-vat-frontend',
        'vat-summary-frontend',
        'vat-agent-client-lookup-frontend',
        'vat-vc-api-stub',
        'vat-obligations',
        'vat-returns',
        'manage-vat-subscription-dynamic-stub',
        'vat-obligations-dynamic-stub',
        'vat-returns-dynamic-stub',
        'financial-transactions',
        'financial-transactions-dynamic-stub'
).build(this)
