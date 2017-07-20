package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtFrontendJobBuilder('fhdds-frontend').
        withTests("test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('fhdds').
        withTests("test").
        build(this as DslFactory)

new BuildMonitorViewBuilder('FHDDS')
        .withJobs('fhdds', 'fhdds-frontend').build(this)
