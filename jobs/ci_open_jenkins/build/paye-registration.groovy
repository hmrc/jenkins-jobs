package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtFrontendJobBuilder('paye-registration-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('PAYE-REGISTRATION-MONITOR')
.withJobs('paye-registration-frontend').build(this)