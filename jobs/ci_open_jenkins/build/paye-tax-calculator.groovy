package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder


new SbtFrontendJobBuilder('paye-tax-calculator-frontend').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)


new BuildMonitorViewBuilder('PAYE-TAX-CALCULATOR-MONITOR')
        .withJobs('paye-tax-calculator-frontend').build(this)
