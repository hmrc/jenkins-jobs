package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtMicroserviceJobBuilder('file-upload').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)

new SbtFrontendJobBuilder('file-upload-frontend').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)

new SbtLibraryJobBuilder('clamav-client').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('file-transfer-stub').
    build(this as DslFactory)


new BuildMonitorViewBuilder('FUAAS-MONITOR')
        .withJobs('file-upload', 'file-upload-frontend', 'clamav-client', 'paye-tax-calculator-frontend').build(this)
