package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.JobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.step.Steps

import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.HtmlReportsPublisher.htmlReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.JUnitReportsPublisher.jUnitReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.scm.GitHubScmTrigger.gitHubScmTrigger
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilders
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.gradleCleanTestPublish

final class GradleLibraryJobBuilder implements Builder {

    private final JobBuilder jobBuilder

    GradleLibraryJobBuilder(String name, String repository = "hmrc/$name") {
        this.jobBuilder = jobBuilders(name, repository).
                                     withScmTriggers(gitHubScmTrigger()).
                                     withSteps(gradleCleanTestPublish()).
                                     withPublishers(htmlReportsPublisher(['build/reports/tests': 'HTML Report']), jUnitReportsPublisher('build/test-results/**/*.xml'))
    }

    Job build(DslFactory dslFactory) {
        jobBuilder.build(dslFactory)
    }
}