package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder

def customsInventoryLinkingImports = 'customs-inventory-linking-imports'
def customsInventoryLinkingExports = 'customs-inventory-linking-exports'
def customsDeclarations = 'customs-declarations'
def customsNotification = "customs-notification"
def customsApiCommon = 'customs-api-common'

private Job microservice(String serviceName) {
    new SbtMicroserviceJobBuilder(serviceName).
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)
}

microservice(customsInventoryLinkingImports)
microservice(customsInventoryLinkingExports)
microservice(customsNotification)
microservice(customsApiCommon)
new SbtMicroserviceJobBuilder(customsDeclarations).
        withSCoverage().
        withScalaStyle().
        withPackage(). // it is a fix to enable zip task in DECs to work
        build(this as DslFactory)

new BuildMonitorViewBuilder('CUSTOMS-MONITOR').
    withJobs(
        customsInventoryLinkingImports,
        customsInventoryLinkingExports,
        customsDeclarations,
        customsNotification,
        customsApiCommon).
    build(this)
