package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder


new SbtMicroserviceJobBuilder('fh-registration').
        withTests("test").
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('fh-registration-frontend').
        withTests("test").
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('FHDDS')
        .withJobs('fh-registration', 'fh-registration-frontend').build(this)
