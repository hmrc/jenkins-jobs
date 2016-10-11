package ci_open_jenkins.build
import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtFrontendJobBuilder('childcare-schemes-interest-frontend').
        withScalaStyle().
        withSCoverage().
        withTests("test").
        build(this as DslFactory)


new BuildMonitorViewBuilder('CSI-MONITOR')
        .withJobs('childcare-schemes-interest-frontend').build(this)
