package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtMicroserviceJobBuilder('iht').
        withScalaStyle().
        withSCoverage().
	withTests("test").
        build(this as DslFactory)

new SbtFrontendJobBuilder('iht-frontend').
        withScalaStyle().
        withSCoverage().
	withTests("test").
        build(this as DslFactory)

new BuildMonitorViewBuilder('IHT-MONITOR')
        .withJobs('iht','iht-frontend').build(this)
