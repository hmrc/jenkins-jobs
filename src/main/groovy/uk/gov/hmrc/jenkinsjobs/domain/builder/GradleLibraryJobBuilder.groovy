package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.JobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.AbsoluteTimeoutWrapper.timeoutWrapper
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.gradleCleanTestPublish

final class GradleLibraryJobBuilder implements Builder {

    private final JobBuilder jobBuilder

    private int timeout = 10

    GradleLibraryJobBuilder(String name, String repository = name) {
        this.jobBuilder = jobBuilder(name, repository).
                                     withSteps(gradleCleanTestPublish())
    }

    GradleLibraryJobBuilder withExtendedTimeout() {
        this.timeout = 20
        this
    }

    Job build(DslFactory dslFactory) {
        jobBuilder.withWrappers(timeoutWrapper(this.timeout)).build(dslFactory)
    }
}