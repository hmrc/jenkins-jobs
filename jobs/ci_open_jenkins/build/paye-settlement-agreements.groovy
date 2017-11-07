package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtMicroserviceJobBuilder('paye-settlement-agreements').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('paye-settlement-agreements-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('PAYE-SETTLEMENT-AGREEMENTS-MONITOR')
        .withJobs('paye-settlement-agreements','paye-settlement-agreements-frontend').build(this)
