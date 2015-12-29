import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtFrontendJobBuilder('pbik-frontend').
                         build(this as DslFactory)

new SbtMicroserviceJobBuilder('pbik').
                         build(this as DslFactory)

new BuildMonitorViewBuilder('PBIK-MONITOR')
        .withJobs('pbik-frontend', 'pbik').build(this)