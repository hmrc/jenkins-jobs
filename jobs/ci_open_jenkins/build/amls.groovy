package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtLibraryJobBuilder('play-whitelist-filter').
        withSCoverage().
        build(this as DslFactory)

new BuildMonitorViewBuilder('AMLS-MONITOR')
        .withJobs('play-whitelist-filter', 'amls-frontend',
        'amls','amls-notification'
).build(this)
