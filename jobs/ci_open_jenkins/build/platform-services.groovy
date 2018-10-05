package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtLibraryJobBuilder('clamav-client').
        build(this as DslFactory)


new SbtMicroserviceJobBuilder('file-transfer-stub').
    build(this as DslFactory)


new BuildMonitorViewBuilder('PLATFORM-SERVICES-OPEN-MONITOR')
        .withJobs('clamav-client',
                  'file-transfer-stub'
        )
        .build(this)
