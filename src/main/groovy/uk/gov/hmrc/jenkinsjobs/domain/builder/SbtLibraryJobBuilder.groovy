package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.JobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.Publisher
import uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable

import static java.util.Arrays.asList
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.BuildDescriptionPublisher.buildDescriptionByRegexPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.CleanWorkspaceStep.cleanWorkspace
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.JDK8
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.defaultHtmlReportsPublisher
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.defaultJUnitReportsPublisher
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.sbtCleanTestPublish

final class SbtLibraryJobBuilder implements Builder<Job> {

    private boolean withJUnitReports = true
    private JobBuilder jobBuilder

    SbtLibraryJobBuilder(String name, JdkEnvironmentVariable jdk = JDK8) {
        jobBuilder = jobBuilder(name, name, jdk).
                                withSteps(sbtCleanTestPublish(), cleanWorkspace())
    }

    SbtLibraryJobBuilder withoutJUnitReports() {
        this.withJUnitReports = false
        this
    }

    Job build(DslFactory dslFactory) {
        jobBuilder.withPublishers(publishers()).
                   build(dslFactory)
    }

    private ArrayList<Publisher> publishers() {
        List<Publisher> publishers = new ArrayList<>(asList(defaultHtmlReportsPublisher(), buildDescriptionByRegexPublisher('.*sbt git versioned as ([\\w\\d\\.\\-]+)')))
        if (withJUnitReports) {
            publishers.add(defaultJUnitReportsPublisher())
        }
        publishers
    }
}