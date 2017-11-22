package uk.gov.hmrc.jenkinsjobs.domain.builder

import uk.gov.hmrc.jenkinsjobbuilders.domain.scm.Scm
import uk.gov.hmrc.jenkinsjobbuilders.domain.step.SbtStep
import uk.gov.hmrc.jenkinsjobbuilders.domain.step.Step

import static uk.gov.hmrc.jenkinsjobbuilders.domain.configure.CucumberReportsPublisher.cucumberReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.configure.XvfbBuildWrapper.xvfbBuildWrapper
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep
import static uk.gov.hmrc.jenkinsjobbuilders.domain.trigger.GitHubPushTrigger.gitHubPushTrigger
import static uk.gov.hmrc.jenkinsjobbuilders.domain.trigger.PollTrigger.pollTrigger

class ZapTestsFollowingJourneyJobBuilder extends AbstractZapTestsJobBuilder<ZapTestsFollowingJourneyJobBuilder> {

    ZapTestsFollowingJourneyJobBuilder(String name, String scm, SbtStep startStep, Step commandStep, SbtStep stopStep, String upstreamJobName) {
        builder = uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder(name, scm, 'master').
                withTriggers(gitHubPushTrigger(), pollTrigger("H/5 * * * *")).
                withConfigures(xvfbBuildWrapper(), cucumberReportsPublisher()).
                withSteps(startStep, copyArtifacts(upstreamJobName), startZap(), commandStep, stopZap(), stopStep).
                withPublishers(uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.defaultHtmlReportsPublisher(), uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.cleanXvfbPostBuildTaskPublisher(), uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.zapArtifactsPublisher())
    }

    protected static Step startZap() {
        shellStep("""\
                    |echo "Starting ZAP"
                    |BUILD_ID=dontKillMe zap -daemon -config api.disablekey=true -port 11000 -dir \$WORKSPACE/results/zap -session zap-journey &
                    |sleep 10
                    """.stripMargin())
    }

    private static Step copyArtifacts(String upstreamJobName) {
        uk.gov.hmrc.jenkinsjobs.domain.step.CopyUpstreamArtifactsStep.copyUpstreamArtifacts(upstreamJobName, "", "results/zap/**", "", "Latest successful build")
    }

}
