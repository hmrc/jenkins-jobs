package ci_open_jenkins.build

import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder

new BuildMonitorViewBuilder('NON-REPUDIATION-BUILD-MONITOR')
        .withJobs('nrs-retrieval-frontend', 'nrs-retrieval').build(this)
