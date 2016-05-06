package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variable.StringEnvironmentVariable.stringEnvironmentVariable

new SbtMicroserviceJobBuilder('self-assessment-api')
        .withEnvironmentVariable(stringEnvironmentVariable("MONGO_TEST_URI", "mongodb://localhost:27017/self-assessment-api"))
        .build(this as DslFactory)


new BuildMonitorViewBuilder('MTD-MONITOR')
        .withJobs('self-assessment-api').build(this)

