package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtFrontendJobBuilder('off-payroll-frontend').
        withTests("test").
        build(this as DslFactory)

new SbtFrontendJobBuilder('off-payroll-analytics-frontend').
        withTests("test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('off-payroll-decision').
        withTests("test").
        build(this as DslFactory)

new BuildMonitorViewBuilder('DDCT-LIVE-SERVICES-MONITOR')
        .withJobs(
        'off-payroll-frontend',
        'off-payroll-analytics-frontend',
        'off-payroll-decision')
        .build(this)
