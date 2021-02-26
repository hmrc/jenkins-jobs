package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtLibraryJobBuilder('logging').build(this as DslFactory)

new SbtLibraryJobBuilder('play-random-json-filter').build(this as DslFactory)

new SbtLibraryJobBuilder('txm-events').build(this as DslFactory)

new SbtFrontendJobBuilder('kafka-amqp-sink').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('TXM-NORTH-MONITOR')
        .withJobs('logging',
                  'play-random-json-filter',
                  'txm-events').build(this)
