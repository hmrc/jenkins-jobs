package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder


new SbtMicroserviceJobBuilder('fh-registration').
        withTests("test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('dfs-aemfd-fhdds').
        withTests("test").
        build(this as DslFactory)

new SbtFrontendJobBuilder('fh-registration-frontend').
        withTests("test").
        build(this as DslFactory)

new BuildMonitorViewBuilder('FHDDS')
        .withJobs('fhdds', 'fh-registration-frontend').build(this)
