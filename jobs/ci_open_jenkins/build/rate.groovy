package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtMicroserviceJobBuilder('back-office-adapter').
                 withScalaStyle().
                 withSCoverage().
        build(this as DslFactory)

new SbtLibraryJobBuilder('xml-parser').
                 withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('RATE-MONITOR')
        .withJobs('xml-parser').build(this)
