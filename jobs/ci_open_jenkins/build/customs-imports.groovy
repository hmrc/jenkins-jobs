package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtFrontendJobBuilder("customs-declare-imports-frontend").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder("customs-declarations-stub").
        build(this as DslFactory)

new SbtLibraryJobBuilder("wco-dec").
        build(this as DslFactory)

new BuildMonitorViewBuilder("CDS-IMPORT-DECLARATIONS-MONITOR")
        .withJobs("customs-declare-imports-frontend", "customs-declarations-stub", "wco-dec")
        .build(this)
