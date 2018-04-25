package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtFrontendJobBuilder('employment-expenses-tax-relief-guidance-frontend').
        withScalaStyle().
        withSCoverage().
        withTests("test").
        build(this as DslFactory)

new BuildMonitorViewBuilder('EETRG-MONITOR').
        withJobs('employment-expenses-tax-relief-guidance-frontend').
        build(this)