package ci_open_jenkins.build

import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtScalaJsLibraryJobBuilder

new SbtScalaJsLibraryJobBuilder('paye-estimator').withoutJUnitReports().build(this)

new BuildMonitorViewBuilder('NGC-MONITOR')
        .withJobs('paye-estimator')
        .build(this)
