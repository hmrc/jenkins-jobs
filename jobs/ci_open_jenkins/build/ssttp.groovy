package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtFrontendJobBuilder('self-service-time-to-pay-frontend').
                         build(this as DslFactory)

new BuildMonitorViewBuilder('SSTTP-MONITOR')
        .withJobs('self-service-time-to-pay-frontend').build(this)

