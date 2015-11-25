package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.JobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.HtmlReportsPublisher.htmlReportsPublisher
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.*
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.sbtCleanDistTgzPublish

final class SbtMicroserviceJobBuilder implements Builder<Job> {

    private JobBuilder jobBuilder
    private String sbtTests = "test it:test"

    SbtMicroserviceJobBuilder(String name) {
        jobBuilder = jobBuilder(name, name).
                                withPublishers(defaultHtmlReportsPublisher(),
                                               bobbyArtifactsPublisher(),
                                               defaultBuildDescriptionPublisher(),
                                               defaultJUnitReportsPublisher())
    }

    Job build(DslFactory dslFactory) {
        jobBuilder.withSteps(sbtCleanDistTgzPublish(sbtTests)).
                   build(dslFactory)
    }

    SbtMicroserviceJobBuilder withTests(String tests) {
        this.sbtTests = tests
        this
    }

    SbtMicroserviceJobBuilder withHtmlReports(Map<String, String> htmlReportDirs) {
        this.jobBuilder = jobBuilder.withPublishers(htmlReportsPublisher(htmlReportDirs))
        this
    }    
}