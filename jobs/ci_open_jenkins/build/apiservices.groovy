package ci_open_jenkins.build
import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtFrontendJobBuilder('api-gatekeeper-frontend').
        withTests("test acceptance:test").
        withXvfb().
        build(this as DslFactory)

new SbtFrontendJobBuilder('deployment-approval-frontend').
        withTests("test acceptance:test").
        withXvfb().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-platform-test-user').
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-platform-test-user-frontend').
        withXvfb().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-platform-test-login-frontend').
        withXvfb().
        build(this as DslFactory)

new BuildMonitorViewBuilder('API-MONITOR')
        .withJobs('api-gatekeeper-frontend',
        'deployment-approval-frontend',
        'api-platform-test-user',
        'api-platform-test-user-frontend',
        'api-platform-test-login-frontend'
).build(this)
