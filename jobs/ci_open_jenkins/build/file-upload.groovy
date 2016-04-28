package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('file-upload').
        build(this as DslFactory)

new SbtLibraryJobBuilder('clamav-client').
        build(this as DslFactory)

new BuildMonitorViewBuilder('DDCW-MONITOR')
        .withJobs('file-upload', 'clamav-client').build(this)
