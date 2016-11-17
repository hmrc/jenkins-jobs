package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtMicroserviceJobBuilder('nisp').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)
                         
new SbtFrontendJobBuilder('nisp-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('nisp-frontend-hotfix', 'nisp-frontend', 'iv-fix').
	withScalaStyle().
	withSCoverage().
	build(this as DslFactory)

new SbtMicroserviceJobBuilder('state-pension').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('NISP-MONITOR')
        .withJobs('nisp','nisp-frontend', 'state-pension').build(this)
