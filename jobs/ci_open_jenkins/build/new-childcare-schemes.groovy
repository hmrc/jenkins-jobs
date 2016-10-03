package ci_open_jenkins.build
import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtFrontendJobBuilder('new-childcare-schemes-frontend').
        withScalaStyle().
        withSCoverage().
        withTests("test").
        build(this as DslFactory)


new BuildMonitorViewBuilder('NEW-CHILDCARE-SCHEMES-MONITOR')
        .withJobs('new-childcare-schemes-frontend').build(this)
