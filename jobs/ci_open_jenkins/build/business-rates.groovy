package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtLibraryJobBuilder('valuetype')
        .withSCoverage()
        .build(this as DslFactory)

new SbtLibraryJobBuilder('htmlcheck')
        .withSCoverage()
        .build(this as DslFactory)

new BuildMonitorViewBuilder('BUSINESS-RATES-MONITOR')
        .withJobs('valuetype')
        .withJobs('htmlcheck')
        .build(this)
