package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtLibraryJobBuilder('play-events').
        build(this as DslFactory)

new SbtLibraryJobBuilder('attachments-client').
        build(this as DslFactory)

new SbtLibraryJobBuilder('ct-calculations').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-time').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('cato-files-proxy').
        build(this as DslFactory)

new BuildMonitorViewBuilder('CATO-MONITOR')
        .withJobs('ct-calculations',
                  'cato-files-proxy',
                  'play-events',
                  'play-time',
                  'attachments-client')
        .build(this)