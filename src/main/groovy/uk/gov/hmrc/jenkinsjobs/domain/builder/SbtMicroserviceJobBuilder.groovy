package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.JobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.variable.EnvironmentVariable

import static uk.gov.hmrc.jenkinsjobbuilders.domain.configure.CheckStyleReportsPublisher.checkStyleReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.configure.SCoverageReportsPublisher.sCoverageReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.HtmlReportsPublisher.htmlReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.AbsoluteTimeoutWrapper.timeoutWrapper
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.*
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.sbtCleanDistTgzPublish

final class SbtMicroserviceJobBuilder implements Builder<Job> {

    private JobBuilder jobBuilder
    private String sbtTests = "test it:test"
    private List<String> beforeTest = new ArrayList<String>()
    private List<String> afterTest = new ArrayList<String>()

    private int timeout = 15

    SbtMicroserviceJobBuilder(String name) {
        jobBuilder = jobBuilder(name, name).
                                withPublishers(defaultHtmlReportsPublisher(),
                                               bobbyArtifactsPublisher(),
                                               defaultBuildDescriptionPublisher(),
                                               defaultJUnitReportsPublisher())
    }

    Job build(DslFactory dslFactory) {
        jobBuilder.withSteps(sbtCleanDistTgzPublish(beforeTest.collect {it + " "}.join(""), sbtTests, afterTest.collect {it + " "}.join(""))).
                withWrappers(timeoutWrapper(this.timeout)).
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

    SbtMicroserviceJobBuilder withSCoverage() {
        beforeTest += "coverage"
        afterTest += "coverageOff"
        afterTest += "coverageReport"
        jobBuilder = jobBuilder.withConfigures(sCoverageReportsPublisher())
        this
    }

    SbtMicroserviceJobBuilder withScalaStyle() {
        beforeTest += "scalastyle"
        jobBuilder = jobBuilder.withConfigures(checkStyleReportsPublisher())
        this
    }

    SbtMicroserviceJobBuilder withLogRotator(int daysToKeep, int numToKeep) {
        jobBuilder = jobBuilder.withLogRotator(daysToKeep, numToKeep)
        this
    }

    SbtMicroserviceJobBuilder withEnvironmentVariable(EnvironmentVariable  environmentVariable) {
        jobBuilder = jobBuilder.withEnvironmentVariables(environmentVariable)
        this
    }

    SbtMicroserviceJobBuilder withExtendedTimeout() {
        this.timeout = 30
        this
    }

    SbtMicroserviceJobBuilder withPackage() {
        afterTest += "package"
        this
    }
}
