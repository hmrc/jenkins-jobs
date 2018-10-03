package ci_open_jenkins.build

import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.ZapTestsFollowingJourneyJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep as shellStep

new BuildMonitorViewBuilder('MTD-API_CI-OPEN_MONITOR')
        .withJobs('self-assessment-api-router', 'self-assessment-api', 'mtd-self-employment').build(this)

new ZapTestsFollowingJourneyJobBuilder('checking-self-assessment-api-zap',
        'self-assessment-api',
        shellStep("""\
         | mkdir -p \${WORKSPACE}/tmp
         | sbt -Dhttp.proxyHost=localhost -Dhttp.proxyPort=11000 \${SBT_OPTS} -mem 8192 clean "func:test-only uk.gov.hmrc.selfassessmentapi.resources.SelfEmploymentsResourceSpec uk.gov.hmrc.ZapRunner" dist-tgz +publishSigned -Djava.io.tmpdir=\${WORKSPACE}/tmp
         """.stripMargin()),
        'self-assessment-api')
        .build(this).disabled()
