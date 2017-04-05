package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
                         
new SbtFrontendJobBuilder('nisp-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('state-pension').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('national-insurance-record').
	withScalaStyle().
	withSCoverage().
	build(this as DslFactory)

new BuildMonitorViewBuilder('NISP-MONITOR')
        .withJobs('nisp-frontend', 'state-pension','national-insurance-record').build(this)
