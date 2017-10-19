package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder

new SbtMicroserviceJobBuilder('agent-access-control').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)
new SbtMicroserviceJobBuilder('agent-client-relationships').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)
new SbtMicroserviceJobBuilder('agent-mapping').
        withSCoverage().
        build(this as DslFactory)
new SbtMicroserviceJobBuilder('agent-subscription').
        withSCoverage().
        build(this as DslFactory)
new SbtFrontendJobBuilder('agent-subscription-frontend').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)
new SbtFrontendJobBuilder('agent-mapping-frontend').
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('agent-services-account-frontend').
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('agent-client-authorisation').
        withSCoverage().
        build(this as DslFactory)
        
new SbtFrontendJobBuilder('agent-fi-agent-frontend').
        withSCoverage().
        build(this as DslFactory)
new SbtFrontendJobBuilder('agent-fi-client-frontend').
        withSCoverage().
        build(this as DslFactory)
new SbtMicroserviceJobBuilder('agent-fi-relationship').
        withSCoverage().
        build(this as DslFactory)
new SbtMicroserviceJobBuilder('agent-fi-access-control').
        withSCoverage().
        build(this as DslFactory)
new SbtMicroserviceJobBuilder('agent-fi-invitation').
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('agent-epaye-registration').
        withSCoverage().
        build(this as DslFactory)
new SbtFrontendJobBuilder('agent-epaye-registration-frontend').
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('agent-kyc').
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('agent-assurance').
        withSCoverage().
        build(this as DslFactory)

def frontendJob = new SbtFrontendJobBuilder('agent-client-authorisation-frontend').
        withSCoverage().
        withTests("test it:test acc:test").
        withXvfb().
        build(this as DslFactory)
frontendJob.environmentVariables {
    env('no_proxy', 'localhost')
}

new SbtLibraryJobBuilder('agent-kenshoo-monitoring').
        withSCoverage().
        build(this as DslFactory)

new SbtLibraryJobBuilder('agent-mtd-identifiers').
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('AGENTS-MONITOR')
        .withJobs('agent-access-control', 'agent-client-authorisation', 'agent-client-authorisation-frontend',
                  'agent-kenshoo-monitoring', 'agent-kyc', 'agent-client-relationships', 'agent-subscription', 'agent-subscription-frontend',
                  'agent-mtd-identifiers','agent-mapping','agent-mapping-frontend', 'agent-services-account-frontend').build(this)
