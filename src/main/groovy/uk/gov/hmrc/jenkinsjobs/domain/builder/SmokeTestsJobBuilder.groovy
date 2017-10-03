package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.JobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.configure.Configure
import uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.Publisher
import uk.gov.hmrc.jenkinsjobbuilders.domain.trigger.Trigger
import uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.AbsoluteTimeoutWrapper

import static uk.gov.hmrc.jenkinsjobbuilders.domain.configure.XvfbBuildWrapper.parallelXvfbBuildWrapper
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.HtmlReportsPublisher.htmlReportsPublisher
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.cleanXvfbPostBuildTaskPublisher
import static uk.gov.hmrc.jenkinsjobs.domain.scm.HmrcGitHubComScm.hmrcGitHubComScm
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.SbtStep.sbtStep

final class SmokeTestsJobBuilder implements Builder<Job> {
    private JobBuilder builder

    private int timeout = 30

    SmokeTestsJobBuilder(String name, String repository, String command) {
        this.builder = jobBuilder(name).
                withScm(hmrcGitHubComScm(repository)).
                withConfigures(parallelXvfbBuildWrapper()).
                withPublishers(cleanXvfbPostBuildTaskPublisher(), htmlReportsPublisher(['target/smoke-test-reports/html-report/': 'Smoke Test Report'])).
                withSteps(sbtStep([command], '\${TMP}'))
    }

    SmokeTestsJobBuilder withPublishers(Publisher... publishers) {
        this.builder = builder.withPublishers(publishers)
        this
    }

    SmokeTestsJobBuilder withConfigures(Configure ... configures) {
        this.builder = builder.withConfigures(configures)
        this
    }

    SmokeTestsJobBuilder withConfigures(List<Configure> configures) {
        this.builder = builder.withConfigures(configures)
        this
    }

    SmokeTestsJobBuilder withExtendedTimeout() {
        this.timeout = 60
        this
    }

    SmokeTestsJobBuilder withExtendedTimeout(minutes) {
        this.timeout = minutes
        this
    }

    SmokeTestsJobBuilder withTriggers(Trigger... triggers) {
        this.builder = builder.withTriggers(triggers)
        this
    }

    Job build(DslFactory dslFactory) {
        builder.withWrappers(AbsoluteTimeoutWrapper.timeoutWrapper(this.timeout)).build(dslFactory)
    }
}
