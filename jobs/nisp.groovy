import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('nisp').
                         build(this as DslFactory)

new BuildMonitorViewBuilder('NISP-MONITOR')
        .withJobs('nisp').build(this)
