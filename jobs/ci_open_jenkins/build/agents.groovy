package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('agent-access-control').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('agent-client-authorisation').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('agent-client-authorisation-frontend').
        build(this as DslFactory)