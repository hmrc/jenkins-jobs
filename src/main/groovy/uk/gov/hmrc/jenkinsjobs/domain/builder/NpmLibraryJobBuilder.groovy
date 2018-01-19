package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.JobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.AbsoluteTimeoutWrapper.timeoutWrapper
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.*
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.npmRelease

final class NpmLibraryJobBuilder implements Builder<Job> {

    private JobBuilder jobBuilder
    private String nodeVersion = "4.8.4"

    private int timeout = 15

    NpmLibraryJobBuilder(String name) {
        this(name, name, "master")
    }

    NpmLibraryJobBuilder(String name, String repository, String branch) {
        jobBuilder = jobBuilder(name, repository, branch).
                                withPublishers(defaultBuildDescriptionPublisher())
    }

    Job build(DslFactory dslFactory) {
        jobBuilder.withSteps(npmRelease(nodeVersion)).
                withWrappers(timeoutWrapper(this.timeout)).
                build(dslFactory)
    }

    NpmLibraryJobBuilder withNodeJsVersion(String version) {
        this.nodeVersion = version
        this
    }
}
