package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtFrontendJobBuilder('manage-vat-subscription-frontend')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtFrontendJobBuilder('view-vat-returns-frontend')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtFrontendJobBuilder('deregister-vat-frontend')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtFrontendJobBuilder('vat-summary-frontend')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('change-of-circumstances')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new BuildMonitorViewBuilder('VATVC-MONITOR').withJobs(
        'change-of-circumstances',
        'deregister-vat-frontend',
        'manage-vat-subscription-frontend',
        'vat-summary-frontend',
        'view-vat-returns-frontend'
).build(this)
