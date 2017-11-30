package uk.gov.hmrc.jenkinsjobs.domain.builder

import uk.gov.hmrc.jenkinsjobbuilders.domain.step.Step

import static uk.gov.hmrc.jenkinsjobbuilders.domain.configure.CucumberReportsPublisher.cucumberReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.configure.XvfbBuildWrapper.xvfbBuildWrapper
import static uk.gov.hmrc.jenkinsjobbuilders.domain.trigger.GitHubPushTrigger.gitHubPushTrigger
import static uk.gov.hmrc.jenkinsjobbuilders.domain.trigger.PollTrigger.pollTrigger

class ZapTestsFollowingJourneyJobBuilder extends AbstractZapTestsJobBuilder<ZapTestsFollowingJourneyJobBuilder> {

    ZapTestsFollowingJourneyJobBuilder(String name, String scm, Step commandStep, String upstreamJobName) {
        builder = uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder(name, scm, 'master').
                withTriggers(gitHubPushTrigger(), pollTrigger("H/5 * * * *")).
                withConfigures(xvfbBuildWrapper(), cucumberReportsPublisher()).
                withSteps(copyArtifacts(upstreamJobName), commandStep).
                withPublishers(uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.defaultHtmlReportsPublisher(), uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.cleanXvfbPostBuildTaskPublisher(), uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.zapArtifactsPublisher())
    }

    private static Step copyArtifacts(String upstreamJobName) {
        uk.gov.hmrc.jenkinsjobs.domain.step.CopyUpstreamArtifactsStep.copyUpstreamArtifacts(upstreamJobName, "", "results/zap/**", "", "Latest successful build")
    }
}
