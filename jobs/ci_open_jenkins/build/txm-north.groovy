package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtLibraryJobBuilder('address-reputation-store').build(this as DslFactory)

new SbtMicroserviceJobBuilder('address-reputation-ingester').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)
                         
new SbtMicroserviceJobBuilder('address-lookup-demo').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)
                         
new BuildMonitorViewBuilder('TXM-NORTH-MONITOR')
        .withJobs('address-reputation-store', 'address-reputation-ingester', 'address-lookup-demo').build(this)

