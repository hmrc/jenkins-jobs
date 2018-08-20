package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtMicroserviceJobBuilder('trust-registration-api').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('trusts-frontend').
        withScalaStyle().
        withSCoverage().
        withXvfb().
        build(this as DslFactory)

new BuildMonitorViewBuilder('TRUST-MONITOR')
        .withJobs('trust-registration-api','trusts-frontend')
        .build(this)
