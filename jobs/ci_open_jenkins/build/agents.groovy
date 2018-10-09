package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder

new SbtMicroserviceJobBuilder('agent-subscription').
        withSCoverage().
        build(this as DslFactory)
new SbtFrontendJobBuilder('agent-subscription-frontend').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('agent-epaye-registration').
        withSCoverage().
        build(this as DslFactory)
new SbtFrontendJobBuilder('agent-epaye-registration-frontend').
        withSCoverage().
        build(this as DslFactory)

new SbtLibraryJobBuilder('agent-kenshoo-monitoring').
        withSCoverage().
        build(this as DslFactory)

new SbtLibraryJobBuilder('agent-mtd-identifiers').
        withSCoverage().
        build(this as DslFactory)
