package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtLibraryJobBuilder('play-conditional-form-mapping').
        build(this as DslFactory)

new SbtFrontendJobBuilder('for-frontend')
        .build(this as DslFactory)

new SbtFrontendJobBuilder('voa-property-linking-frontend')
        .withSCoverage()
        .build(this as DslFactory)

new SbtFrontendJobBuilder('voa-property-linking')
        .withSCoverage()
        .build(this as DslFactory)

new SbtFrontendJobBuilder('voa-property-linking-hotfix', 'voa-property-linking', 'vpl-hotfix')
        .build(this as DslFactory)

new SbtFrontendJobBuilder('voa-property-linking-frontend-hotfix', 'voa-property-linking-frontend', 'vplf-hotfix')
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('business-rates-authorisation')
        .withSCoverage()
        .build(this as DslFactory)