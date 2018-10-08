package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

        

new SbtLibraryJobBuilder('play-scheduling').
        build(this)
        

new SbtLibraryJobBuilder('work-item-repo-26').
        build(this)


new SbtLibraryJobBuilder('metrix-26').
        build(this)



new SbtLibraryJobBuilder('batch-updater').
        build(this)

new BuildMonitorViewBuilder('DC-PUBLIC-OTHER')
        .withJobs(
                  'play-scheduling',
                  'batch-updater'
                  )
        .build(this)
