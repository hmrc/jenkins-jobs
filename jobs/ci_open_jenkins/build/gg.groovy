package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('phone-number').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('wire-mock-stub').
        withScalaStyle().
        withSCoverage().
        withTests("it:test").
        build(this as DslFactory)

new BuildMonitorViewBuilder('GG-OPEN')
        .withJobs('wire-mock-stub')
        .build(this)
