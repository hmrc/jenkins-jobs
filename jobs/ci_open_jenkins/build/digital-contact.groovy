package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtLibraryJobBuilder('a-b-test').
        build(this as DslFactory)

new SbtLibraryJobBuilder('url-builder').
        build(this as DslFactory)
        
new SbtLibraryJobBuilder('emailaddress').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-scheduling').
        build(this)
        
new SbtLibraryJobBuilder('batch-updater').
        build(this)

new BuildMonitorViewBuilder('DC-MONITOR')
        .withJobs('a-b-test',
                  'url-builder',
                  'emailaddress',
                  'play-scheduling',
                  'batch-updater'
                  ).build(this)
