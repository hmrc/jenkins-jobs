import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtFrontendJobBuilder('tax-account-router-frontend').
                         build(this as DslFactory)

new BuildMonitorViewBuilder('TAR-MONITOR')
        .withJobs('tax-account-router-frontend').build(this)

new SbtLibraryJobBuilder('performance-test-runner')
        .build(this as DslFactory)
