package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new BuildMonitorViewBuilder('CTUTR-Monitor')
        .withJobs(
        'request-corporation-tax-number-frontend',
        'request-corporation-tax-number')
        .build(this as DslFactory)

new SbtFrontendJobBuilder('request-corporation-tax-number-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('request-corporation-tax-number').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

