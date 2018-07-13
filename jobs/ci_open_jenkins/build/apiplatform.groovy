package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.configure.CucumberReportsPublisher.cucumberReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.configure.XvfbBuildWrapper.xvfbBuildWrapper
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.HtmlReportsPublisher.htmlReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.*


new SbtMicroserviceJobBuilder('api-definition').
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-documentation').
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-documentation-frontend').
        withSCoverage().
        withTests("test acceptance:test").
        withXvfb().
        build(this as DslFactory)

/*
jobBuilder('api-documentation-frontend', 'api-documentation-frontend', 'master').
    withConfigures(xvfbBuildWrapper(), cucumberReportsPublisher()).
    withSteps(shellStep('${WORKSPACE}/jenkins-start-with-assets-frontend.sh')).
    withPublishers(
            defaultJUnitReportsPublisher(),
            defaultBuildDescriptionPublisher(),
            bobbyArtifactsPublisher(),
            htmlReportsPublisher(['target/acceptance-reports/cucumber': 'Acceptance Report']),
            cleanXvfbPostBuildTaskPublisher()).
    build(this)
*/

new SbtMicroserviceJobBuilder('api-example-microservice').
        withSCoverage().
        withTests("test it:test component:test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-example-scala-client').
        withTests("test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-gateway').
        withTests("test it:test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-simulator').
        withTests("test it:test").
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
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-platform-test-user').
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-platform-test-user-frontend').
        withSCoverage().
        withXvfb().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-platform-test-login-frontend').
        withSCoverage().
        withXvfb().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-publisher').
        withTests("test it:test").
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-scope').
        withSCoverage().
        withTests("test it:test").
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

new SbtMicroserviceJobBuilder('national-insurance-des-stub').
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('service-locator').
        withSCoverage().
        withTests("test it:test").
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
        'api-platform-test-user-frontend',
        'api-platform-test-login-frontend',
        'api-publisher',
        'api-scope',
        'api-subscription-fields',
        'api-notification-pull',
        'api-notification-queue',
        'national-insurance-des-stub',
        'service-locator',
        'totp-generator',
        'play-json-union-formatter',
        'raml-tools',
        'http-metrics'
).build(this)
