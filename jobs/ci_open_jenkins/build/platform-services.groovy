package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder


new SbtMicroserviceJobBuilder('contact-admin').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('upscan-initiate').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('upscan-notify').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('upscan-verify').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)


new BuildMonitorViewBuilder('PLATFORM-SERVICES-MONITOR')
        .withJobs(
                  'contact-admin',
                  'upscan-initiate',
                  'upscan-notify',
                  'upscan-verify'
        )
        .build(this)
