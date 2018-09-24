package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtFrontendJobBuilder('research-recording-tool-frontend').
        withExtendedTimeout().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('vat-sign-up').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('vat-sign-up-frontend').
        withScalaStyle().
        withSCoverage().
        withExtendedTimeout().
        build(this as DslFactory)

new BuildMonitorViewBuilder('SABR-MONITOR').withJobs(
        'income-tax-subscription-store', 'vat-sign-up-frontend', 'vat-sign-up'
).build(this)
