package uk.gov.hmrc.jenkinsjobs.domain.step

import uk.gov.hmrc.jenkinsjobbuilders.domain.step.Step

import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.GradleStep.gradleStep
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.SbtStep.sbtStep
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep

class Steps {

    private Steps() {}

    static Step gradleCleanTestPublish() {
        gradleStep('clean test bintrayUpload --info')
    }

    static Step sbtCleanTestPublish() {
        sbtStep("clean test publishSigned")
    }

    static Step sbtCleanTestItTestDistPublish() {
        sbtStep("clean test it:test dist publishSigned")
    }

    static Step sbtCleanTestItTestDistTgzPublish() {
        sbtStep("clean test it:test dist-tgz publishSigned")
    }


    static Step sbtCleanDistPublish(String tests) {
        sbtStep("clean $tests dist publishSigned")
    }

    static Step sbtCleanDistTgzPublish(String tests) {
        sbtStep("clean $tests dist-tgz publishSigned")
    }

    static Step createARelease() {
        shellStep("""
                  |if [ ! -f "~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION/releaser_2.11-\$RELEASER_VERSION-assembly.jar" ]; then
                  |  mkdir -p ~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION
                  |  curl -L -k -o ~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION/releaser_2.11-\$RELEASER_VERSION-assembly.jar https://dl.bintray.com/hmrc/releases/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION/releaser_2.11-\$RELEASER_VERSION-assembly.jar
                  |fi
                  |java -jar ~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION/releaser_2.11-\$RELEASER_VERSION-assembly.jar \$ARTEFACT_NAME \$RELEASE_CANDIDATE_VERSION \$RELEASE_TYPE
                  """.stripMargin())
    }

    static Step initARepository(String newRepositoryName, String teamName) {
        shellStep("""
                  |if [ ! -f "~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$INIT_REPO_VERSION/init-repository_2.11-\$INIT_REPO_VERSION-assembly.jar" ]; then
                  |  mkdir -p ~/.m2/repository/uk/gov/hmrc/init-repository_2.11/\$INIT_REPO_VERSION
                  |  curl -L -k -o ~/.m2/repository/uk/gov/hmrc/init-repository_2.11/\$INIT_REPO_VERSION/init-repository_2.11-\$INIT_REPO_VERSION-assembly.jar https://dl.bintray.com/hmrc/releases/uk/gov/hmrc/init-repository_2.11/\$INIT_REPO_VERSION/init-repository_2.11-\$INIT_REPO_VERSION-assembly.jar
                  |fi
                  |java -jar ~/.m2/repository/uk/gov/hmrc/init-repository_2.11/\$INIT_REPO_VERSION/init-repository_2.11-\$INIT_REPO_VERSION-assembly.jar $newRepositoryName $teamName
                  """.stripMargin())
    }

    static Step initARepositoryPy(String name) {
        shellStep("""
                  |python init-repository.py $name
                  """.stripMargin())
    }
}