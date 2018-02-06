package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtLibraryJobBuilder('accessibility-driver').
        build(this as DslFactory)

new SbtFrontendJobBuilder('pertax-frontend').
		withScalaStyle().
		withSCoverage().
		withNodeJs("4.8.4").
		build(this as DslFactory)

new SbtFrontendJobBuilder('feedback-survey-frontend').
		        withScalaStyle().
		        withSCoverage().
		        build(this as DslFactory)

new BuildMonitorViewBuilder('PERTAX-MONITOR')
        .withJobs('pertax-frontend', 'feedback-survey-frontend').build(this)

