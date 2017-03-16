package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder


new SbtMicroserviceJobBuilder('fset-faststream').
    withExtendedTimeout().
    build(this as DslFactory)

new SbtFrontendJobBuilder('fset-faststream-frontend').
    build(this as DslFactory)

new SbtFrontendJobBuilder('fset-launchpad-gateway').
    build(this as DslFactory)

new SbtFrontendJobBuilder('fset-parity-gateway').
    build(this as DslFactory)

new SbtMicroserviceJobBuilder('fset-fasttrack').
    withExtendedTimeout().
    build(this as DslFactory)

new SbtFrontendJobBuilder('fset-fasttrack-frontend').
    build(this as DslFactory)

new SbtMicroserviceJobBuilder('fset-email-renderer').
    build(this as DslFactory)


new BuildMonitorViewBuilder('FSET-MONITOR').
    withJobs('fset-faststream', 'fset-faststream-frontend', 'fset-parity-gateway', 'fset-launchpad-gateway', 'fset-fasttrack', 'fset-fasttrack-frontend', 'fset-email-renderer').
    build(this)
