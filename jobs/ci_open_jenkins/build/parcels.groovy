package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new BuildMonitorViewBuilder('PARCELS-MONITOR')
        .withJobs('parcels-payment').build(this)

new SbtMicroserviceJobBuilder('parcels-payment').
        withScalaStyle().
        withTests("test").
        build(this as DslFactory)


