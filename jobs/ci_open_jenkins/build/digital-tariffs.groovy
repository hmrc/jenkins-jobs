package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('binding-tariff-classification').
        withTests("test it:test").
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('DIGITAL-TARIFFS-MONITOR')
        .withJobs(
            'binding-tariff-classification'
        ).build(this)
