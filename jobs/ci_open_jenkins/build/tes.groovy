package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtFrontendJobBuilder('personal-tax-summary').build(this as DslFactory)

new BuildMonitorViewBuilder('TES-MONITOR')
		.withJobs('personal-tax-summary').build(this)


