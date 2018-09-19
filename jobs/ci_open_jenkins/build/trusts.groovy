package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder



new SbtFrontendJobBuilder('trusts-frontend').
        withScalaStyle().
        withSCoverage().
        withXvfb().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('trusts').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('TRUST-MONITOR')
        .withJobs('trusts-frontend', 'trusts')
        .build(this)
