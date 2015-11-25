package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.JobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.HtmlReportsPublisher.htmlReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.JUnitReportsPublisher.jUnitReportsPublisher
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.gradleCleanTestPublish

final class GradleLibraryJobBuilder implements Builder {

    private final JobBuilder jobBuilder

    GradleLibraryJobBuilder(String name, String repository = name) {
        this.jobBuilder = jobBuilder(name, repository).
                                     withSteps(gradleCleanTestPublish())
    }

    Job build(DslFactory dslFactory) {
        jobBuilder.build(dslFactory)
    }
}