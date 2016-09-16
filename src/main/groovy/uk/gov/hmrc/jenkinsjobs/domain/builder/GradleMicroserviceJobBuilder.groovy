package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.JobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.AbsoluteTimeoutWrapper.timeoutWrapper
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.gradleHtmlReportsPublisher
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.gradleJUnitReportsPublisher
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.gradleCleanDistTgzPublish

class GradleMicroserviceJobBuilder implements Builder<Job> {

    private JobBuilder jobBuilder

    private int timeout = 15

    GradleMicroserviceJobBuilder(String name) {
        jobBuilder = jobBuilder(name, name).withPublishers(gradleHtmlReportsPublisher(),
                                                            gradleJUnitReportsPublisher())
    }

    @Override
    Job build(DslFactory dslFactory) {
        jobBuilder.withSteps(gradleCleanDistTgzPublish()).
                withWrappers(timeoutWrapper(this.timeout)).
                build(dslFactory)
    }

    GradleMicroserviceJobBuilder withExtendedTimeout() {
        this.timeout = 30
        this
    }
}
