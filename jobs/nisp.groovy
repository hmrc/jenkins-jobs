import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtMicroserviceJobBuilder('nisp').
                         build(this as DslFactory)
                         
new SbtFrontendJobBuilder('nisp-frontend').
                         build(this as DslFactory)

new BuildMonitorViewBuilder('NISP-MONITOR')
        .withJobs('nisp','nisp-frontend').build(this)
