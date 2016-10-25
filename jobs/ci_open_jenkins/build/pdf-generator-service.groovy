package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('pdf-generator-service').
        build(this as DslFactory)


new BuildMonitorViewBuilder('PGS-MONITOR')
        .withJobs('pdf-generator-service').build(this)
