package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtFrontendJobBuilder('investment-tax-relief-submission-frontend').
                 withScalaStyle().
                 withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('investment-tax-relief-submission').
                 withScalaStyle().
                 withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('investment-tax-relief-subscription-frontend').
		 withScalaStyle().
		 withSCoverage().
	build(this as DslFactory)

new SbtMicroserviceJobBuilder('investment-tax-relief-subscription').
                 withScalaStyle().
                 withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('investment-tax-relief-submission-dynamic-stub').
                 withScalaStyle().
                 withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('investment-tax-relief-subscription-dynamic-stub').
                 withScalaStyle().
                 withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('investment-tax-relief-agent-frontend').
                 withScalaStyle().
                 withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('TAVC-MONITOR')
        .withJobs('investment-tax-relief-submission-frontend','investment-tax-relief-submission','investment-tax-relief-subscription-frontend','investment-tax-relief-subscription','investment-tax-relief-submission-dynamic-stub','investment-tax-relief-subscription-dynamic-stub','investment-tax-relief-agent-frontend').build(this)