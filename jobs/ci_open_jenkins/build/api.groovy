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

new SbtFrontendJobBuilder('api-revocation-frontend').
        withTests("test acceptance:test").
        withXvfb().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-platform-test-user-frontend').
        withXvfb().
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-platform-test-login-frontend').
        withXvfb().
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-json-union-formatter').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-platform-test-user').
        build(this as DslFactory)

new BuildMonitorViewBuilder('API-MONITOR')
        .withJobs('api-example-scala-client', 'api-revocation-frontend', 'play-json-union-formatter', 'api-platform-test-user', 'api-platform-test-user-frontend').build(this)
