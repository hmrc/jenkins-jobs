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


new BuildMonitorViewBuilder('TAVC-MONITOR')
        .withJobs('investment-tax-relief-submission-frontend','investment-tax-relief-submission').build(this)