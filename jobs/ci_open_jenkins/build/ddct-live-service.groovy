package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtFrontendJobBuilder('pensions-lifetime-allowance-frontend')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtFrontendJobBuilder('pensions-lifetime-allowance-frontend-hotfix', 'pensions-lifetime-allowance-frontend',
        'hotfix/pla-frontend')
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('pensions-lifetime-allowance')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('pla-dynamic-stub')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

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
        'capital-gains-calculator',
        'cgt-calculator-non-resident-frontend',
        'cgt-calculator-resident-shares-frontend',
        'cgt-calculator-resident-properties-frontend',
        'pensions-lifetime-allowance',
        'pensions-lifetime-allowance-frontend',
        'pla-dynamic-stub',
        'off-payroll-frontend',
        'off-payroll-analytics-frontend',
        'off-payroll-decision')
        .build(this)