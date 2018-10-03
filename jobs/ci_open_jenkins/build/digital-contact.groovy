package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtLibraryJobBuilder('url-builder').
        build(this as DslFactory)
        
new SbtLibraryJobBuilder('emailaddress').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-scheduling').
        build(this)
        
new SbtLibraryJobBuilder('work-item-repo').
        build(this)

new SbtLibraryJobBuilder('work-item-repo-26').
        build(this)

new SbtLibraryJobBuilder('metrix').
        build(this)

new SbtLibraryJobBuilder('metrix-26').
        build(this)



new BuildMonitorViewBuilder('DC-PUBLIC-OTHER')
        .withJobs('emailaddress',
                  'url-builder',                 
                  'play-scheduling',
                  'work-item-repo',
                  'metrix'  
                  )
        .build(this)
