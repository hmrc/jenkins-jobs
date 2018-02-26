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

new SbtMicroserviceJobBuilder('des-simulator')
		.withTests("test func:test")
		.withEnvironmentVariable(stringEnvironmentVariable("MONGO_TEST_URI", "mongodb://localhost:12345/des-simulator"))
		.build(this as DslFactory)

new BuildMonitorViewBuilder('MTD-MONITOR')
        .withJobs('self-assessment-api', 'vat-api').build(this)

