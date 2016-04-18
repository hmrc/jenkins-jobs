package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('self-assessment-api').
        build(this as DslFactory)

new BuildMonitorViewBuilder('MTD-MONITOR')
        .withJobs('self-assessment-api').build(this)

