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

new SbtMicroserviceJobBuilder('awrs-notification')
        .withScalaStyle()
        .withSCoverage()
        .withTests("test")
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('awrs')
        .withScalaStyle()
        .withSCoverage()
        .withTests("test")
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('awrs-lookup')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtFrontendJobBuilder('awrs-lookup-frontend')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtFrontendJobBuilder('awrs-frontend')
        .withScalaStyle()
        .withSCoverage()
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

new SbtMicroserviceJobBuilder('c2ni')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtFrontendJobBuilder('c2ni-frontend')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('ei')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtFrontendJobBuilder('ei-frontend')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('epaye-api').
        withTests("test").
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('LIVE-SERVICES-MONITOR')
        .withJobs(
        'apprenticeship-levy',
        'awrs-frontend',
        'awrs',
        'awrs-notification',
        'awrs-lookup',
        'awrs-lookup-frontend',
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
        'tamc',
        'tamc-frontend',
        'marriage-allowance-des-stub',
        'ated-subscription',
        'agent-client-mandate',
        'agent-client-mandate-frontend',
        'ated-subscription-frontend',
        'ated-frontend',
        'ated',
        'c2ni',
        'c2ni-frontend',
        'ei',
        'ei-frontend',
        'epaye-api'
        )
        .build(this)
