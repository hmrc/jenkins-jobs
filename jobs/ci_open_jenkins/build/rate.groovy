package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('back-office-adapter').
                 withScalaStyle().
                 withSCoverage().
        build(this as DslFactory)

new SbtLibraryJobBuilder('xml-parser').
        build(this as DslFactory)

new BuildMonitorViewBuilder('RATE-MONITOR')
        .withJobs('back-office-adapter', 'xml-parser').build(this)
