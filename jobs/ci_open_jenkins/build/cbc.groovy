package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtFrontendJobBuilder("cbcr-frontend").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder("cbcr").
        build(this as DslFactory)

new SbtFrontendJobBuilder("cbcr-frontend-develop", "cbcr-frontend","develop").
        build(this as DslFactory)

new SbtFrontendJobBuilder("cbcr-develop", "cbcr", "develop").
        build(this as DslFactory)


new BuildMonitorViewBuilder("COUNTRY-BY-COUNTRY-OPEN-MONITOR").withJobs("cbcr-frontend", "cbcr", "cbcr-frontend-develop", "cbcr-develop").build(this)