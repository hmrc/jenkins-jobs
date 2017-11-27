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

new SbtMicroserviceJobBuilder('vat-api')
        .withTests("test func:test")
        .withEnvironmentVariable(stringEnvironmentVariable("MONGO_TEST_URI", "mongodb://localhost:27017/vat-api"))
        .build(this as DslFactory)

new BuildMonitorViewBuilder('MTD-MONITOR')
        .withJobs('self-assessment-api', 'vat-api').build(this)

new ZapTestsFollowingJourneyJobBuilder('checking-self-assessment-api-zap',
        'self-assessment-api',
        shellStep("start"),
        shellStep("sbt \"func:test-only uk.gov.hmrc.ZapRunner\""),
        shellStep("stop"),
        'self-assessment-api')
        .build(this).disabled()
