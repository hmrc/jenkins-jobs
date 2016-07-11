package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtMicroserviceJobBuilder('agent-access-control').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('agent-client-authorisation').
        build(this as DslFactory)

new SbtFrontendJobBuilder('agent-client-authorisation-frontend').
        build(this as DslFactory)
