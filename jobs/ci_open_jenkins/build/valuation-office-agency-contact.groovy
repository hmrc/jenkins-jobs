package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('lightweight-contact-events').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

// TODO: Add acceptance test job

new BuildMonitorViewBuilder('VOAC-MONITOR')
        .withJobs('lightweight-contact-events').build(this)
