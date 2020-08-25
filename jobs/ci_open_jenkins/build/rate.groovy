package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtLibraryJobBuilder('akka-xml-parser').
                 withSCoverage().
        build(this as DslFactory)

new SbtLibraryJobBuilder('service-contract-cache').
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('RATE-MONITOR')
        .withJobs('akka-xml-parser', 'xml-parser', 'service-contract-cache').build(this)
