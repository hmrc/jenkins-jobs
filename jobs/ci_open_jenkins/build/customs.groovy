package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder

def customsInventoryLinkingImports = 'customs-inventory-linking-imports'
def customsInventoryLinkingExports = 'customs-inventory-linking-exports'
def customsDeclarations = 'customs-declarations'
def customsApiCommon = 'customs-api-common'

new SbtMicroserviceJobBuilder(customsInventoryLinkingImports).
    withSCoverage().
    withScalaStyle().
    build(this as DslFactory)

new SbtMicroserviceJobBuilder(customsInventoryLinkingExports).
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder(customsDeclarations).
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder(customsApiCommon).
    withSCoverage().
    withScalaStyle().
    build(this as DslFactory)

new BuildMonitorViewBuilder('CUSTOMS-MONITOR').
    withJobs(
        customsInventoryLinkingImports,
        customsInventoryLinkingExports,
        customsDeclarations,
        customsApiCommon).
    build(this)
