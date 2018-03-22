package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder

def customsDitLicences = 'customs-dit-licences'
def customsInventoryLinkingImports = 'customs-inventory-linking-imports'
def customsInventoryLinkingExports = 'customs-inventory-linking-exports'
def customsDeclarations = 'customs-declarations'
def customsNotification = "customs-notification"
def customsApiCommon = 'customs-api-common'

private Job microserviceWithPackage(String serviceName) {
    new SbtMicroserviceJobBuilder(serviceName).
        withSCoverage().
        withScalaStyle().
            withPackage(). // it is a fix to enable zip task in DECs and ILE to work
        build(this as DslFactory)
}

private Job microservice(String serviceName) {
    new SbtMicroserviceJobBuilder(serviceName).
            withSCoverage().
            withScalaStyle().
            build(this as DslFactory)
}

microservice(customsDitLicences)
microservice(customsInventoryLinkingImports)
microserviceWithPackage(customsInventoryLinkingExports)
microservice(customsNotification)
microservice(customsApiCommon)
microserviceWithPackage(customsDeclarations)


new BuildMonitorViewBuilder('CUSTOMS-MONITOR').
    withJobs(
        customsDitLicences,
        customsInventoryLinkingImports,
        customsInventoryLinkingExports,
        customsDeclarations,
        customsNotification,
        customsApiCommon).
    build(this)
