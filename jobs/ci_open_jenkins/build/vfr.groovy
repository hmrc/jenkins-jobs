package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder

new SbtFrontendJobBuilder('vat-flat-rate-calculator-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('VAT-FLAT-RATE-CALCULATOR-MONITOR')
        .withJobs('vat-flat-rate-calculator-frontend').build(this)

