package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.JobBuilder

import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.*
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.sbtCleanTestItTestDistTgzPublish
import static uk.gov.hmrc.jenkinsjobbuilders.domain.configure.CheckStyleReportsPublisher.checkStyleReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.configure.SCoverageReportsPublisher.sCoverageReportsPublisher

final class SbtFrontendJobBuilder implements Builder<Job> {

    private JobBuilder jobBuilder
    private List<String> beforeTest = new ArrayList<String>()
    private List<String> afterTest = new ArrayList<String>()

    SbtFrontendJobBuilder(String name) {
        jobBuilder = jobBuilder(name, name).
                                withPublishers(defaultHtmlReportsPublisher(),
                                               defaultBuildDescriptionPublisher(),
                                               defaultJUnitReportsPublisher(),
                                               bobbyArtifactsPublisher())
    }

    Job build(DslFactory dslFactory) {
        jobBuilder.withSteps(sbtCleanTestItTestDistTgzPublish(beforeTest.collect {it + " "}.join(""), afterTest.collect {it + " "}.join(""))).
                build(dslFactory)
    }

    SbtFrontendJobBuilder withSCoverage() {
        beforeTest += "coverage"
        afterTest += "coverageOff"
        jobBuilder = jobBuilder.withConfigures(sCoverageReportsPublisher())
        this
    }

    SbtFrontendJobBuilder withScalaStyle() {
        beforeTest += "scalastyle"
        jobBuilder = jobBuilder.withConfigures(checkStyleReportsPublisher())
        this
    }
}
