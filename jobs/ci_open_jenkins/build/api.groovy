package ci_open_jenkins.build
import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtLibraryJobBuilder('play-json-union-formatter').
        build(this as DslFactory)

new SbtFrontendJobBuilder('api-gateway').
        withTests("test it:test").
        withXvfb().
        build(this as DslFactory)

new BuildMonitorViewBuilder('API-MONITOR')
        .withJobs(
        'play-json-union-formatter',
        'api-gateway'
).build(this)
