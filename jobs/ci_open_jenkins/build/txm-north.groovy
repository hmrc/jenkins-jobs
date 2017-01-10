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

new SbtMicroserviceJobBuilder('address-reputation-ingester').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('address-lookup-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('bank-account-reputation-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('maps').
        withScalaStyle().
        build(this as DslFactory)

new SbtFrontendJobBuilder('example-play-25-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('TXM-NORTH-MONITOR')
        .withJobs('logging',
                  'address-reputation-store',
                  'address-reputation-ingester',
                  'address-lookup-frontend',
                  'bank-account-reputation-frontend',
                  'maps',
                  'example-play-25-frontend',
                  'play-random-json-filter',
                  'txm-events').build(this)
