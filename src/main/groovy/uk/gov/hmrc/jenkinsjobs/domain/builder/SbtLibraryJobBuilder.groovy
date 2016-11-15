package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.JobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.AbsoluteTimeoutWrapper.timeoutWrapper
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.*
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.sbtCleanTestPublish
import static uk.gov.hmrc.jenkinsjobbuilders.domain.configure.SCoverageReportsPublisher.sCoverageReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.trigger.CronTrigger.cronTrigger

final class SbtLibraryJobBuilder implements Builder<Job> {

    private boolean withJUnitReports = true
    private JobBuilder jobBuilder
    private List<String> beforeTest = new ArrayList<String>()
    private List<String> afterTest = new ArrayList<String>()

    private int timeout = 10

    SbtLibraryJobBuilder(String name) {
        this(name, name, 'master')
    }

    SbtLibraryJobBuilder(String name, String repository, String branch) {
        jobBuilder = jobBuilder(name, repository, branch).
                                withPublishers(defaultHtmlReportsPublisher(),
                                               bobbyArtifactsPublisher(),
                                               defaultBuildDescriptionPublisher())
    }

    SbtLibraryJobBuilder withoutJUnitReports() {
        this.withJUnitReports = false
        this
    }

    SbtLibraryJobBuilder withSCoverage() {
        beforeTest += "coverage"
        afterTest += "coverageOff"
        afterTest += "coverageReport"
        jobBuilder = jobBuilder.withConfigures(sCoverageReportsPublisher())
        this
    }

    SbtLibraryJobBuilder withExtendedTimeout() {
        this.timeout = 20
        this
    }

    SbtLibraryJobBuilder withCronTrigger(String cronExpression) {
        jobBuilder = jobBuilder.withTriggers(cronTrigger(cronExpression))
        this
    }

    Job build(DslFactory dslFactory) {
        jobBuilder = jobBuilder
                .withSteps(sbtCleanTestPublish(beforeTest.collect {it + " "}.join(""), afterTest.collect {it + " "}.join("")))

        if (withJUnitReports) {
            jobBuilder = jobBuilder.withPublishers(defaultJUnitReportsPublisher())
        }

        jobBuilder.withWrappers(timeoutWrapper(this.timeout)).build(dslFactory)
    }
}