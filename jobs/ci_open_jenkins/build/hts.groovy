package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('help-to-save').
        withScalaStyle().
        withSCoverage().
        withTests("test").
        build(this as DslFactory)

new SbtFrontendJobBuilder('help-to-save-frontend').
        withExtendedTimeout().
        withScalaStyle().
        withSCoverage().
        withTests("test").
        build(this as DslFactory)

new SbtFrontendJobBuilder('help-to-save-stub').
        withScalaStyle().
        withSCoverage().
        withTests("test").
        build(this as DslFactory)

new BuildMonitorViewBuilder('HELP-TO-SAVE-MONITOR')
        .withJobs('help-to-save','help-to-save-frontend', 'help-to-save-stub').build(this)

