package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtFrontendJobBuilder('api-example-scala-client').
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-gatekeeper-frontend').
        withTests("test acceptance:test").
        withHtmlReports('target/acceptance-test-reports/': 'Acceptance tests HTML Report').
        withXvfb().
        build(this as DslFactory)

new BuildMonitorViewBuilder('API-MONITOR')
        .withJobs('api-example-scala-client', 'api-gatekeeper-frontend').build(this)
