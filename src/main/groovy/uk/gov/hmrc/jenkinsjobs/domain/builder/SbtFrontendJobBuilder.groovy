package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.JobBuilder

import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.*
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.sbtCleanTestItTestDistTgzPublish

final class SbtFrontendJobBuilder implements Builder<Job> {

    private JobBuilder jobBuilder

    SbtFrontendJobBuilder(String name) {
        jobBuilder = jobBuilder(name, name).
                                withSteps(sbtCleanTestItTestDistTgzPublish()).
                                withPublishers(defaultHtmlReportsPublisher(),
                                               defaultBuildDescriptionPublisher(),
                                               defaultJUnitReportsPublisher(),
                                               bobbyArtifactsPublisher())
    }

    Job build(DslFactory dslFactory) {
        jobBuilder.build(dslFactory)
    }
}