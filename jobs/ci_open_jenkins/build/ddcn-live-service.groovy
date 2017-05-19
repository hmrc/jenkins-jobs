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

new SbtFrontendJobBuilder('childcare-schemes-interest-frontend')
        .withScalaStyle()
        .withSCoverage()
        .withTests("test")
        .build(this as DslFactory)

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

new SbtMicroserviceJobBuilder('paac')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtFrontendJobBuilder('paac-frontend')
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

new SbtFrontendJobBuilder('vat-flat-rate-calculator-frontend')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new BuildMonitorViewBuilder('LIVE-SERVICES-MONITOR')
        .withJobs(
        'apprenticeship-levy',
        'childcare-schemes-interest-frontend',
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
        'paac',
        'paac-frontend',
        'pbik',
        'pbik-frontend',
        'pensions-lifetime-allowance',
        'pensions-lifetime-allowance-frontend',
        'pla-dynamic-stub',
        'tamc',
        'tamc-frontend',
        'vat-flat-rate-calculator-frontend')
        .build(this)