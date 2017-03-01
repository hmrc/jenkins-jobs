package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.JobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.configure.XvfbBuildWrapper.xvfbBuildWrapper
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.HtmlReportsPublisher.htmlReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.AbsoluteTimeoutWrapper.timeoutWrapper
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.*
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.sbtCleanDistTgzPublish
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.sbtCleanTestItTestDistTgzPublish
import static uk.gov.hmrc.jenkinsjobbuilders.domain.configure.CheckStyleReportsPublisher.checkStyleReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.configure.SCoverageReportsPublisher.sCoverageReportsPublisher

final class SbtFrontendJobBuilder implements Builder<Job> {

    private JobBuilder jobBuilder
    private String sbtTests = "test it:test"
    private List<String> beforeTest = new ArrayList<String>()
    private List<String> afterTest = new ArrayList<String>()

    private int timeout = 15

    SbtFrontendJobBuilder(String name) {
        this(name, name, 'master')
    }

    SbtFrontendJobBuilder(String name, String repository, String branch) {
        jobBuilder = jobBuilder(name, repository, branch).
                                withPublishers(defaultHtmlReportsPublisher(),
                                               defaultBuildDescriptionPublisher(),
                                               defaultJUnitReportsPublisher(),
                                               bobbyArtifactsPublisher())
    }
    
    Job build(DslFactory dslFactory) {
        jobBuilder.withSteps(sbtCleanDistTgzPublish(beforeTest.collect {it + " "}.join(""), sbtTests, afterTest.collect {it + " "}.join(""))).
        withWrappers(timeoutWrapper(this.timeout)).
        build(dslFactory)
    }


    SbtFrontendJobBuilder withSCoverage() {
        beforeTest += "coverage"
        afterTest += "coverageOff"
        afterTest += "coverageReport"
        jobBuilder = jobBuilder.withConfigures(sCoverageReportsPublisher())
        this
    }

    SbtFrontendJobBuilder withScalaStyle() {
        beforeTest += "scalastyle"
        jobBuilder = jobBuilder.withConfigures(checkStyleReportsPublisher())
        this
    }

    SbtFrontendJobBuilder withTests(String tests) {
        this.sbtTests = tests
        this
    }

    SbtFrontendJobBuilder withLogRotator(int daysToKeep, int numToKeep) {
        jobBuilder = jobBuilder.withLogRotator(daysToKeep, numToKeep)
        this
    }

    SbtFrontendJobBuilder withHtmlReports(Map<String, String> htmlReportDirs) {
        this.jobBuilder = jobBuilder.withPublishers(htmlReportsPublisher(htmlReportDirs))
        this
    }

    SbtFrontendJobBuilder withXvfb() {
        this.jobBuilder = jobBuilder.withConfigures(xvfbBuildWrapper()).
                withPublishers(cleanXvfbPostBuildTaskPublisher())
        this
    }

    SbtFrontendJobBuilder withExtendedTimeout() {
        this.timeout = 30
        this
    }
}
