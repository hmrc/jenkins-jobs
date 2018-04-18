package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.JobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.HtmlReportsPublisher.htmlReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.JUnitReportsPublisher.jUnitReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.trigger.PollTrigger.pollTrigger
import static uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.AbsoluteTimeoutWrapper.timeoutWrapper
import static uk.gov.hmrc.jenkinsjobs.domain.scm.HmrcGitHubComScm.hmrcGitHubComScm
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.gradleCleanTestBuild

final class GradleLibraryJobBuilder implements Builder {

    private final JobBuilder jobBuilder

    private int timeout = 10

    GradleLibraryJobBuilder(String name, String repository = name) {
        this.jobBuilder = JobBuilders.jobBuilder(name)
            .withScm(hmrcGitHubComScm(repository, 'master'))
            .withTriggers(pollTrigger("H/5 * * * *"))
            .withLabel('single-executor')
            .withSteps(gradleCleanTestBuild())
            .withPublishers(jUnitReportsPublisher("build/test-results/test/*.xml"),
                            htmlReportsPublisher(["build/reports/tests/test/" : "HTML Test report"]))
    }

    GradleLibraryJobBuilder withExtendedTimeout() {
        this.timeout = 20
        this
    }

    Job build(DslFactory dslFactory) {
        jobBuilder.withWrappers(timeoutWrapper(this.timeout)).build(dslFactory)
    }
}
