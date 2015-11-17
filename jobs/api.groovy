import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtFrontendJobBuilder('api-example-scala-client').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('hello-world').
        withTests("test it:test component:test").
        withHtmlReports('target/component-reports/cucumber': 'Functional tests HTML Report').
        build(this as DslFactory)



new BuildMonitorViewBuilder('API-MONITOR')
        .withJobs('api-example-scala-client',
                  'hello-world')
        .build(this)
