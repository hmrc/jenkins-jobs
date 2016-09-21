package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtMicroserviceJobBuilder('tamc').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)
                         
new SbtFrontendJobBuilder('tamc-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('LIVE-SERVICES-MONITOR')
        .withJobs('tamc','tamc-frontend').build(this)
