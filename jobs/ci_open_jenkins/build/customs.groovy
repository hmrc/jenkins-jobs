package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder

def customsDeclarations = 'customs-declarations'
def apiNotificationPull = 'api-notification-pull'

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

microserviceWithPackage(customsDeclarations)
microservice(apiNotificationPull)

new BuildMonitorViewBuilder('CUSTOMS-MONITOR').
    withJobs(
        customsDeclarations,
        apiNotificationPull).
    build(this)
