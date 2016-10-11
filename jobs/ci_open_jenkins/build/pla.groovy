package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtFrontendJobBuilder('pensions-lifetime-allowance-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('pensions-lifetime-allowance-frontend-hotfix', 'pensions-lifetime-allowance-frontend', 'hotfix/pla-frontend').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('pensions-lifetime-allowance').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('pla-dynamic-stub').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('PLA-MONITOR')
        .withJobs('pensions-lifetime-allowance-frontend','pensions-lifetime-allowance','pla-dynamic-stub').build(this)