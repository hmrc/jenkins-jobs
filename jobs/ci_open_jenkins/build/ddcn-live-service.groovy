package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

def appLevyJob = new SbtMicroserviceJobBuilder('apprenticeship-levy')
        .withTests('test it:test')
        .withSCoverage()
        .withScalaStyle()
        .build(this as DslFactory)
appLevyJob

new SbtFrontendJobBuilder('ers-checking-frontend')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtFrontendJobBuilder('ers-returns-frontend')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('ers-file-validator')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('ers-submissions')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtFrontendJobBuilder('gmp-frontend')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtFrontendJobBuilder('gmp-frontend-hotfix', 'gmp-frontend', 'hotfix/GMP-####')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('gmp')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('gmp-bulk')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtFrontendJobBuilder('nisp-frontend')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('state-pension')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('national-insurance-record')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('pbik')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtFrontendJobBuilder('pbik-frontend')
        .withScalaStyle()
//.withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('tamc')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('tamc-frontend')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtLibraryJobBuilder('tabular-data-validator')
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('marriage-allowance-des-stub').
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('ated-subscription').
        withTests("test").
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('agent-client-mandate').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('agent-client-mandate-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('ated-subscription-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('ated-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('ated').
        withTests("test").
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('epaye-api').
        withTests("test").
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('off-payroll-decision').
        withTests("test").
        build(this as DslFactory)

new SbtFrontendJobBuilder('off-payroll-frontend').
        withTests("test").
        build(this as DslFactory)


new SbtFrontendJobBuilder('tax-summaries-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('tax-summaries').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('tax-summaries-agent').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('pdf-generator-frontend').
        withTests("test").
        withLogRotator(7, 1000).
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('pdf-generator-service').
        withTests("test").
        withLogRotator(7, 1000).
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('ras-api').
        withTests("test").
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('ras-frontend').
        withTests("test").
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('LIVE-SERVICES-MONITOR')
        .withJobs(
        'apprenticeship-levy',
        'ers-checking-frontend',
        'ers-returns-frontend',
        'ers-file-validator',
        'ers-submissions',
        'gmp',
        'gmp-bulk',
        'gmp-frontend',
        'nisp-frontend',
        'state-pension',
        'national-insurance-record',
        'pbik',
        'pbik-frontend',
        'tamc',
        'tamc-frontend',
        'marriage-allowance-des-stub',
        'ated-subscription',
        'agent-client-mandate',
        'agent-client-mandate-frontend',
        'ated-subscription-frontend',
        'ated-frontend',
        'ated',
        'ei',
        'ei-frontend',
        'epaye-api',
        'pdf-generator-frontend',
        'pdf-generator-service',
        'ras-api',
        'ras-frontend'
)
        .build(this)

new BuildMonitorViewBuilder('DDCNLS-TEAM-1-ALL-OPEN')
        .withJobs(
        'tax-summaries-frontend',
        'tax-summaries',
        'tax-summaries-agent')
        .build(this)

new BuildMonitorViewBuilder('DDCNLS-TEAM-4-ALL-OPEN')
        .withJobs(
        'gmp',
        'gmp-bulk',
        'gmp-frontend',
        'ras-api',
        'ras-frontend')
        .build(this)
