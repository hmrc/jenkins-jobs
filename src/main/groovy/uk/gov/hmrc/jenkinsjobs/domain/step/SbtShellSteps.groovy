package uk.gov.hmrc.jenkinsjobs.domain.step

import uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep
import uk.gov.hmrc.jenkinsjobbuilders.domain.step.Step

import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep


class SbtShellSteps {

    private ShellSteps() {}

    static Step cleanTestPublish() {
        shellStep("sbt clean test publishSigned")
    }
}
