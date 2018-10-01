package ci_open_jenkins.build

import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder

new BuildMonitorViewBuilder('HELP-TO-SAVE-MONITOR')
        .withJobs('help-to-save','help-to-save-frontend', 'help-to-save-stub', 'help-to-save-proxy', 'help-to-save-stride-frontend', 'help-to-save-api', 'help-to-save-test-admin-frontend').build(this)

