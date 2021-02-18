package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtLibraryJobBuilder('logging').build(this as DslFactory)

new SbtLibraryJobBuilder('address-reputation-store').build(this as DslFactory)

new SbtLibraryJobBuilder('play-random-json-filter').build(this as DslFactory)

new SbtLibraryJobBuilder('txm-events').build(this as DslFactory)

new SbtMicroserviceJobBuilder('address-lookup').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('address-lookup-frontend').
        build(this as DslFactory)

new SbtFrontendJobBuilder('address-lookup-frontend-stub').
        build(this as DslFactory)

new SbtFrontendJobBuilder('kafka-amqp-sink').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('TXM-NORTH-MONITOR')
        .withJobs('logging',
                  'address-reputation-store',
                  'address-lookup',
                  'address-lookup-frontend',
                  'address-lookup-frontend-stub',
                  'play-random-json-filter',
                  'txm-events').build(this)
