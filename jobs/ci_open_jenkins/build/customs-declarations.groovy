package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder


new SbtFrontendJobBuilder('customs-declare-exports-frontend').build(this as DslFactory)

new BuildMonitorViewBuilder('CUSTOMS-DECLARATIONS-MONITOR').withJobs('customs-declare-exports-frontend').build(this)