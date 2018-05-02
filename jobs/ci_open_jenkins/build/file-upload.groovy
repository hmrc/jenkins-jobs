package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder


new SbtFrontendJobBuilder('fuaas-api-mock-up-frontend').
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('FUAAS-MONITOR')
        .withJobs('file-upload', 'file-upload-frontend', 'clamav-client').build(this)
