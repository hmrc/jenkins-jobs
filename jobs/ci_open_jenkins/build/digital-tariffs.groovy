package ci_open_jenkins.build

import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder

new BuildMonitorViewBuilder('DIGITAL-TARIFFS-MONITOR')
        .withJobs().build(this)
