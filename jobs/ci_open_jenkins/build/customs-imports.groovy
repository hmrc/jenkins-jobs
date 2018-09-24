package ci_open_jenkins.build

import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep

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

jobBuilder("customs-declare-imports-frontend-accessibility-tests", "customs-declare-imports-frontend").withSteps(
        shellStep("sm --start CDS_IMPORTS_ALL -f"),
        shellStep("nvm use 8.12.0"),
        shellStep("npm test")
).build(this as DslFactory)


new BuildMonitorViewBuilder("CDS-IMPORT-DECLARATIONS-MONITOR")
        .withJobs("customs-declare-imports-frontend", "customs-declarations-stub", "wco-dec")
        .build(this)
