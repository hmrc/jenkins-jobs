package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.JobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.StringParameter.stringParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.BuildDescriptionPublisher.buildDescriptionByTextPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.HtmlReportsPublisher.htmlReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.JUnitReportsPublisher.jUnitReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.AbsoluteTimeoutWrapper.timeoutWrapper
import static uk.gov.hmrc.jenkinsjobs.domain.scm.HmrcGitHubComScm.hmrcGitHubComScm
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.gradleCleanTestPublish

final class GradleLibraryReleaseJobBuilder implements Builder {

    private final JobBuilder jobBuilder

    private int timeout = 10

  GradleLibraryReleaseJobBuilder(String name, String repository = name) {
        this.jobBuilder = JobBuilders.jobBuilder("${name}-release")
            .withScm(hmrcGitHubComScm(repository, '${TAG}'))
            .withLabel('single-executor')
            .withParameters(stringParameter("TAG", null, "The tag to build and release e.g. release/11.0.0"))
            .withSteps(gradleCleanTestPublish())
            .withPublishers(jUnitReportsPublisher("build/test-results/test/*.xml"),
                            htmlReportsPublisher(["build/reports/tests/test/" : "HTML Test report"]),
                            buildDescriptionByTextPublisher('${TAG}'))
    }

    GradleLibraryReleaseJobBuilder withExtendedTimeout() {
        this.timeout = 20
        this
    }

    Job build(DslFactory dslFactory) {
        jobBuilder.withWrappers(timeoutWrapper(this.timeout)).build(dslFactory)
    }
}
