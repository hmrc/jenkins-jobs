package uk.gov.hmrc.jenkinsjobs.domain.step

import uk.gov.hmrc.jenkinsjobbuilders.domain.step.Step

import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.GradleStep.gradleStep
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep

class Steps {

    private ShellSteps() {}

    static Step gradleCleanTestPublish() {
        gradleStep('clean test bintrayUpload --info')
    }

    static Step sbtCleanTestPublish() {
        shellStep("sbt clean test publishSigned")
    }

    static Step createARelease() {
        shellStep('export RELEASER_VERSION=0.4.0\n' +
                  'if [ ! -f "~/.m2/repository/uk/gov/hmrc/releaser_2.11/$RELEASER_VERSION/releaser_2.11-$RELEASER_VERSION-assembly.jar" ]; then\n' +
                  '  mkdir -p ~/.m2/repository/uk/gov/hmrc/releaser_2.11/$RELEASER_VERSION\n' +
                  '  curl -L -k -o ~/.m2/repository/uk/gov/hmrc/releaser_2.11/$RELEASER_VERSION/releaser_2.11-$RELEASER_VERSION-assembly.jar https://bintray.com/artifact/download/hmrc/releases/uk/gov/hmrc/releaser_2.11/$RELEASER_VERSION/releaser_2.11-$RELEASER_VERSION-assembly.jar\n' +
                  'fi\n' +
                  '\n' +
                  'java -jar ~/.m2/repository/uk/gov/hmrc/releaser_2.11/$RELEASER_VERSION/releaser_2.11-$RELEASER_VERSION-assembly.jar $ARTEFACT_NAME $RELEASE_CANDIDATE_VERSION $RELEASE_TYPE')
    }
}
