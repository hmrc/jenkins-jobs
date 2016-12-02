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
        build(this as DslFactory)
new SbtMicroserviceJobBuilder('agencies-fake').
        build(this as DslFactory)
new SbtMicroserviceJobBuilder('agent-subscription').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('agent-client-authorisation').
        build(this as DslFactory)

def frontendJob = new SbtFrontendJobBuilder('agent-client-authorisation-frontend').
        withTests("test it:test acc:test").
        withXvfb().
        build(this as DslFactory)
frontendJob.environmentVariables {
    env('no_proxy', 'localhost')
}

new SbtFrontendJobBuilder('agent-performance-test-frontend').
        withTests("test").
        build(this as DslFactory)

new SbtLibraryJobBuilder('agent-kenshoo-monitoring').
        build(this as DslFactory)

new BuildMonitorViewBuilder('AGENTS-MONITOR')
        .withJobs('agent-access-control', 'agent-client-authorisation', 'agent-client-authorisation-frontend',
                  'agent-kenshoo-monitoring', 'agent-client-relationships', 'agencies-fake', 'agent-subscription').build(this)
