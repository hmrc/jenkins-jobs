package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder

def sdesBulkDownload = 'secure-data-exchange-bulk-download'
def sdesProxy = 'secure-data-exchange-proxy'

private Job microservice(String serviceName) {
    new SbtMicroserviceJobBuilder(serviceName).
            withSCoverage().
            withScalaStyle().
            build(this as DslFactory)
}

microservice(sdesBulkDownload)
microservice(sdesProxy)


new BuildMonitorViewBuilder('SDES-MONITOR').
    withJobs(
        sdesBulkDownload,
        sdesProxy).
    build(this)
