package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder


new SbtLibraryJobBuilder('clamav-client').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('contact-admin').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('file-transfer-stub').
    build(this as DslFactory)

new SbtMicroserviceJobBuilder('file-upload').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)

new SbtFrontendJobBuilder('file-upload-frontend').
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

new SbtMicroserviceJobBuilder('upscan-stub').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)

new BuildMonitorViewBuilder('PLATFORM-SERVICES-OPEN-MONITOR')
        .withJobs('clamav-client',
                  'contact-admin',
                  'file-transfer-stub',
                  'file-upload',
                  'file-upload-frontend',
                  'upscan-initiate',
                  'upscan-notify',
                  'upscan-verify',
                  'upscan-stub'
        )
        .build(this)
