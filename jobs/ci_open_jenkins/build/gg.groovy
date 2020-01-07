package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder


new SbtMicroserviceJobBuilder('wire-mock-stub').
        withTests("it:test").
        build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-service-manager').
        withoutJUnitReports().
        build(this)

new BuildMonitorViewBuilder('GG-OPEN')
        .withJobs('wire-mock-stub', 'sbt-service-manager')
        .build(this)
