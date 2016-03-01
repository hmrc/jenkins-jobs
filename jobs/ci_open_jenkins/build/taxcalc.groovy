package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtMicroserviceJobBuilder('taxcalc').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)


new BuildMonitorViewBuilder('TAXCALC-MONITOR')
        .withJobs('taxcalc').build(this)
