package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.JobBuilder

import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.*
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.sbtCleanTestPublish

final class SbtLibraryJobBuilder implements Builder<Job> {

    private boolean withJUnitReports = true
    private JobBuilder jobBuilder

    SbtLibraryJobBuilder(String name) {
        this(name, name, 'master')
    }

    SbtLibraryJobBuilder(String name, String repository, String branch) {
        jobBuilder = jobBuilder(name, repository, branch).
                                withSteps(sbtCleanTestPublish()).
                                withPublishers(defaultHtmlReportsPublisher(),
                                               bobbyArtifactsPublisher(),
                                               defaultBuildDescriptionPublisher())
    }

    SbtLibraryJobBuilder withoutJUnitReports() {
        this.withJUnitReports = false
        this
    }

    Job build(DslFactory dslFactory) {
        if (withJUnitReports) {
            jobBuilder = jobBuilder.withPublishers(defaultJUnitReportsPublisher())
        }

        jobBuilder.build(dslFactory)
    }
}