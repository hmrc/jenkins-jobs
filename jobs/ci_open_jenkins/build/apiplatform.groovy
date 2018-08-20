package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('api-definition').
        withTests("test").
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-documentation-frontend').
        withSCoverage().
        withTests("test acceptance:test").
        withXvfb().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-example-microservice').
        withSCoverage().
        withTests("test it:test component:test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-example-scala-client').
        withTests("test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-gateway').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-simulator').
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-revocation-frontend').
        withSCoverage().
        withTests("test acceptance:test").
        withXvfb().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-gatekeeper-frontend').
        withSCoverage().
        withTests("test acceptance:test").
        withXvfb().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-service-approval-frontend').
        withTests("test acceptance:test").
        withXvfb().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-sandbox-holding-page-frontend').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-platform-test').
        withTests("test").
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-platform-test-user').
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-platform-test-login-frontend').
        withSCoverage().
        withXvfb().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-publisher').
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-scope').
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-stop-autodeploy-test').
        withTests("test").
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-subscription-fields').
        withTests("test it:test acceptance:test").
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-notification-queue').
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-notification-pull').
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('ciao-multisegment-api').
        withTests("test").
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('national-insurance-des-stub').
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('service-locator').
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('third-party-developer-frontend').
        withSCoverage().
        withTests("test it:test component:test").
        withXvfb().
        build(this as DslFactory)

new SbtLibraryJobBuilder('totp-generator').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-json-union-formatter').
        build(this as DslFactory)

new SbtLibraryJobBuilder('raml-tools').
        withSCoverage().
        build(this as DslFactory)

new SbtLibraryJobBuilder('http-metrics').
        withSCoverage().
        build(this as DslFactory)

new SbtLibraryJobBuilder('fraud-prevention').
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('API-MONITOR')
        .withJobs(
        'api-definition',
        'api-documentation',
        'api-documentation-frontend',
        'api-example-microservice',
        'api-example-scala-client',
        'api-gateway',
        'api-simulator',
        'api-revocation-frontend',
        'api-gatekeeper-frontend',
        'api-service-approval-frontend',
        'api-platform-test',
        'api-platform-test-user',
        'api-platform-test-login-frontend',
        'api-publisher',
        'api-scope',
        'api-stop-autodeploy-test',
        'api-subscription-fields',
        'api-notification-pull',
        'api-notification-queue',
        'ciao-multisegment-api',
        'national-insurance-des-stub',
        'service-locator',
        'third-party-developer-frontend',
        'totp-generator',
        'play-json-union-formatter',
        'raml-tools',
        'http-metrics',
        'fraud-prevention'
).build(this)
