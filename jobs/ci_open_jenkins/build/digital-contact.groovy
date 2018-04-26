package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

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

new SbtLibraryJobBuilder('work-item-repo').
        build(this)

new SbtLibraryJobBuilder('metrix').
        build(this)

new SbtLibraryJobBuilder('csp-client').
        build(this)

new SbtMicroserviceJobBuilder('updated-print-suppressions').
        build(this as DslFactory)

new SbtFrontendJobBuilder('customer-advisors-frontend').
        build(this as DslFactory)

new SbtFrontendJobBuilder('preferences-admin-frontend').
        build(this as DslFactory)

new SbtFrontendJobBuilder('announcement-frontend').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('hmrc-email-renderer').
        build(this as DslFactory)

new BuildMonitorViewBuilder('DC-PUBLIC-MONITOR')
        .withJobs(
                  'updated-print-suppressions',
                  'customer-advisors-frontend',
                  'preferences-admin-frontend',
                  'hmrc-email-renderer',
                  'announcement-frontend')
        .build(this)

new BuildMonitorViewBuilder('DC-PUBLIC-OTHER')
        .withJobs('emailaddress',
                  'url-builder',                 
                  'play-scheduling',
                  'batch-updater',
                  'work-item-repo',
                  'metrix',                  
                  'csp-client'                  
                  )
        .build(this)
