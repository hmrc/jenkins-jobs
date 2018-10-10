package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder

def customsDitLicences = 'customs-dit-licences'
def customsDeclarations = 'customs-declarations'
def customsNotification = "customs-notification"
def apiNotificationPull = 'api-notification-pull'
def apiNotificationQueue = 'api-notification-queue'

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
microservice(customsNotification)
microserviceWithPackage(customsDeclarations)
microservice(apiNotificationPull)
microservice(apiNotificationQueue)

new BuildMonitorViewBuilder('CUSTOMS-MONITOR').
    withJobs(
        customsDitLicences,
        customsDeclarations,
        customsNotification,
        apiNotificationPull,
        apiNotificationQueue).
    build(this)
