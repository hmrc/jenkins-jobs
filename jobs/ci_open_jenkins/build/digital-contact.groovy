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

new SbtMicroserviceJobBuilder('secure-message-renderer').
        build(this as DslFactory)

new SbtFrontendJobBuilder('customer-advisors-frontend').
        build(this as DslFactory)

new SbtFrontendJobBuilder('preferences-admin-frontend').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('hmrc-email-renderer').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('message-renderer-template').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('email-renderer-template').
        build(this as DslFactory)

new BuildMonitorViewBuilder('DC-MONITOR')
        .withJobs('a-b-test',
                  'url-builder',
                  'emailaddress',
                  'play-scheduling',
                  'batch-updater',
                  'work-item-repo',
                  'metrix',
                  'updated-print-suppressions',
                  'secure-message-renderer',
                  'customer-advisors-frontend',
                  'preferences-admin-frontend',
                  'csp-client',
                  'hmrc-email-renderer',
                  'message-renderer-template',
                  'email-renderer-template')
        .build(this)
