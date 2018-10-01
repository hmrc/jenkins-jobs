package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder


new SbtFrontendJobBuilder('research-recording-tool-frontend').
        withExtendedTimeout().
        build(this as DslFactory)


new SbtFrontendJobBuilder('vat-sign-up-frontend').
        withScalaStyle().
        withSCoverage().
        withExtendedTimeout().
        build(this as DslFactory)

new BuildMonitorViewBuilder('SABR-MONITOR').withJobs(
        'vat-sign-up-frontend'
).build(this)
