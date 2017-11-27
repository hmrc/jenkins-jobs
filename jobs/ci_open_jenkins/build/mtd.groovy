package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variable.StringEnvironmentVariable.stringEnvironmentVariable

new SbtMicroserviceJobBuilder('self-assessment-api')
        .withTests("test func:test")
        .withEnvironmentVariable(stringEnvironmentVariable("MONGO_TEST_URI", "mongodb://localhost:27017/self-assessment-api"))
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('vat-api')
        .withTests("test func:test")
        .withEnvironmentVariable(stringEnvironmentVariable("MONGO_TEST_URI", "mongodb://localhost:27017/vat-api"))
        .build(this as DslFactory)

new BuildMonitorViewBuilder('MTD-MONITOR')
        .withJobs('self-assessment-api', 'vat-api').build(this)

new ZapTestsFollowingJourneyJobBuilder('checking-self-assessment-api-zap',
        GitHubScm('sef-assessment-api'),
        sbtStep("-Dhttp.proxyHost=localhost -Dhttp.proxyPort=11000 $SBT_OPTS -mem 8192 clean \"func:test-only uk.gov.hmrc.selfassessmentapi.resources.SelfEmploymentsResourceSpec\" dist-tgz +publishSigned -Djava.io.tmpdir=${TMP}", "coverageReport", "dist-tgz publish"),
        Step("sbt \"func:test-only uk.gov.hmrc.ZapRunner\""),
        sbtStep("stop", "coverageReport", "dist-tgz publish"),
        'self-assessment-api')
        .build(this).disabled()
