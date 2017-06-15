package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtLibraryJobBuilder('accessibility-driver').
        build(this as DslFactory)

new SbtFrontendJobBuilder('pertax-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('PERTAX-MONITOR')
        .withJobs('pertax-frontend').build(this)
