package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtFrontendJobBuilder('api-service-approval-frontend').
        withTests("test acceptance:test").
        withXvfb().
        build(this as DslFactory)

new BuildMonitorViewBuilder('API-MONITOR')
        .withJobs(
        'api-documentation',
        'api-documentation-frontend',
        'api-example-microservice',
        'api-example-scala-client',
        'api-gateway',
        'api-revocation-frontend',
        'api-service-approval-frontend',
        'api-platform-test-user',
        'api-platform-test-login-frontend',
        'api-subscription-fields',
        'national-insurance-des-stub',
        'third-party-developer-frontend',
).build(this)
