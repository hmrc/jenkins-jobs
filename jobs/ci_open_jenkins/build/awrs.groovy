package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtMicroserviceJobBuilder('api-integration-test-runner').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('awrs-notification').
        withScalaStyle().
        withSCoverage().
        withTests("test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('awrs').
        withScalaStyle().
        withSCoverage().
        withTests("test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('awrs-lookup').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('awrs-lookup-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('awrs-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('AWRS-MONITOR')
        .withJobs('awrs-frontend','awrs','awrs-notification','api-integration-test-runner','awrs-lookup','awrs-lookup-frontend').build(this)
