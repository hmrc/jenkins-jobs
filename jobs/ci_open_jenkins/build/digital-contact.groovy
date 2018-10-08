package ci_open_jenkins.build

import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtLibraryJobBuilder('batch-updater').
        build(this)

new BuildMonitorViewBuilder('DC-PUBLIC-OTHER')
        .withJobs(
                  'batch-updater'
                  )
        .build(this)
