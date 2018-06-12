package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep as shellStep
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.ZapTestsFollowingJourneyJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variable.StringEnvironmentVariable.stringEnvironmentVariable

new SbtMicroserviceJobBuilder('self-assessment-api')
        .withTests("test func:test")
        .withEnvironmentVariable(stringEnvironmentVariable("MONGO_TEST_URI", "mongodb://localhost:27017/self-assessment-api"))
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('self-assessment-api-router')
        .withTests("test it:test")
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('mtd-tax-calculation')
        .withTests("test it:test")
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('vat-api')
        .withSCoverage()
        .withTests("test func:test")
        .withEnvironmentVariable(stringEnvironmentVariable("MONGO_TEST_URI", "mongodb://localhost:27017/vat-api"))
        .build(this as DslFactory)

new BuildMonitorViewBuilder('MTD-VAT-MONITOR')
        .withJobs('vat-api').build(this)

new BuildMonitorViewBuilder('MTD-SA-MONITOR')
        .withJobs('self-assessment-api-router, self-assessment-api', 'mtd-tax-calculation').build(this)

new ZapTestsFollowingJourneyJobBuilder('checking-self-assessment-api-zap',
        'self-assessment-api',
        shellStep("""\
         | mkdir -p \${WORKSPACE}/tmp
         | sbt -Dhttp.proxyHost=localhost -Dhttp.proxyPort=11000 \${SBT_OPTS} -mem 8192 clean "func:test-only uk.gov.hmrc.selfassessmentapi.resources.SelfEmploymentsResourceSpec uk.gov.hmrc.ZapRunner" dist-tgz +publishSigned -Djava.io.tmpdir=\${WORKSPACE}/tmp
         """.stripMargin()),
        'self-assessment-api')
        .build(this).disabled()
