package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('api-integration-test-runner').
        build(this as DslFactory)

new BuildMonitorViewBuilder('AWRS-MONITOR')
        .withJobs('api-integration-test-runner').build(this)

new SbtMicroserviceJobBuilder('awrs-notification').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)
