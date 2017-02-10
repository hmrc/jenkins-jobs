package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

/*
new SbtMicroserviceJobBuilder('cbc-backend').
        withScalaStyle().
        withSCoverage().
	withTests("test").
        build(this as DslFactory)
*/

new SbtFrontendJobBuilder('cbc-frontend').
		withExtendedTimeout().
        withScalaStyle().
        withSCoverage().
	withTests("test").
        build(this as DslFactory)

new BuildMonitorViewBuilder('CBC-MONITOR')
        .withJobs('cbc','cbc-frontend').build(this)
