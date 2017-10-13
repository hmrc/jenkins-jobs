package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('lightweight-contact-events').
        withScalaStyle().
        build(this as DslFactory)

new SbtFrontendJobBuilder('valuation-office-agency-contact-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

// TODO: Add acceptance test job

new BuildMonitorViewBuilder('VOA_CONTACT')
        .withJobs('lightweight-contact-events',
        'valuation-office-agency-contact-frontend').build(this)