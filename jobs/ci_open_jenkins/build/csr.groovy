package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder


new SbtMicroserviceJobBuilder('csr-faststream').
    build(this as DslFactory)
    
new SbtFrontendJobBuilder('csr-faststream-frontend').
    build(this as DslFactory)


new SbtMicroserviceJobBuilder('csr-fasttrack').
    build(this as DslFactory)

new SbtFrontendJobBuilder('csr-fasttrack-frontend').
    build(this as DslFactory)


new BuildMonitorViewBuilder('CSR-MONITOR').
    withJobs('csr-faststream', 'csr-faststream-frontend', 'csr-fasttrack', 'csr-fasttrack-frontend').
    build(this)
