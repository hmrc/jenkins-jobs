package ci_open_jenkins.build
import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtFrontendJobBuilder('api-gatekeeper-frontend').
        withTests("test acceptance:test").
        withXvfb().
        build(this as DslFactory)

new BuildMonitorViewBuilder('API-MONITOR')
        .withJobs('api-gatekeeper-frontend').build(this)
