package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtFrontendJobBuilder('nrs-retrieval-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('nrs-retrieval')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new BuildMonitorViewBuilder('NON-REPUDIATION-BUILD-MONITOR')
        .withJobs('nrs-retrieval-frontend', 'nrs-retrieval').build(this)
