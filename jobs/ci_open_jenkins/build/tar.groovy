package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.*
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.sbtCleanDistTgzPublish
import static uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.AbsoluteTimeoutWrapper.timeoutWrapper
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.JobBuilder

def microserviceName = "tax-account-router-frontend"

jobBuilder = jobBuilder(microserviceName, microserviceName, 'master').
        withPublishers(defaultHtmlReportsPublisher(),
                defaultBuildDescriptionPublisher(),
                defaultJUnitReportsPublisher(),
                bobbyArtifactsPublisher())
        .withSteps(sbtCleanDistTgzPublish("-Dwebdriver.chrome.driver=/usr/local/bin/chromedriver","test it:test", "")).
        withWrappers(timeoutWrapper(15)).
        build(this)

new BuildMonitorViewBuilder('TAR-MONITOR')
        .withJobs(microserviceName).build(this)

new SbtLibraryJobBuilder('performance-test-runner')
        .build(this as DslFactory)
