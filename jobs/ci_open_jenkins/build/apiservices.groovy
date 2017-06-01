package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtFrontendJobBuilder('api-example-scala-client').
        withTests("test").
        withXvfb().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-gateway').
        withTests("test it:test").
        withXvfb().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-revocation-frontend').
        withTests("test acceptance:test").
        withXvfb().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-gatekeeper-frontend').
        withTests("test acceptance:test").
        withXvfb().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-service-approval-frontend').
        withTests("test acceptance:test").
        withXvfb().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-platform-test').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-platform-test-user').
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-platform-test-user-frontend').
        withXvfb().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-platform-test-login-frontend').
        withXvfb().
        build(this as DslFactory)

new SbtLibraryJobBuilder('totp-generator').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-json-union-formatter').
        build(this as DslFactory)

new BuildMonitorViewBuilder('API-MONITOR')
        .withJobs(
        'api-example-scala-client',
        'api-gateway',
        'api-revocation-frontend',
        'api-gatekeeper-frontend',
        'api-service-approval-frontend',
        'api-platform-test',
        'api-platform-test-user',
        'api-platform-test-user-frontend',
        'api-platform-test-login-frontend',
        'totp-generator',
        'play-json-union-formatter'
).build(this)
