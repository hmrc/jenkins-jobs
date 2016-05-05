package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variable.StringEnvironmentVariable.stringEnvironmentVariable

class MtdSbtMicroserviceJobBuilder {
    private final SbtMicroserviceJobBuilder delegateJob

    public MtdSbtMicroserviceJobBuilder(SbtMicroserviceJobBuilder jobBuilder) {
        this.delegateJob = jobBuilder
    }

    Job build(DslFactory dslFactory) {
        delegateJob.jobBuilder.withEnvironmentVariables(stringEnvironmentVariable("USE_DISK_MONGO", "TRUE"))
        delegateJob.build(dslFactory)
    }
}

new MtdSbtMicroserviceJobBuilder(new SbtMicroserviceJobBuilder('self-assessment-api'))
        .build(this as DslFactory)


new BuildMonitorViewBuilder('MTD-MONITOR')
        .withJobs('self-assessment-api').build(this)

