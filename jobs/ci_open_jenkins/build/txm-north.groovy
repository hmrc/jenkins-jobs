package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtMicroserviceJobBuilder('address-lookup-ingestor').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('address-lookup-demo').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)
                         
new BuildMonitorViewBuilder('TXM-NORTH-MONITOR')
        .withJobs('address-lookup-ingestor', 'address-lookup-demo').build(this)

