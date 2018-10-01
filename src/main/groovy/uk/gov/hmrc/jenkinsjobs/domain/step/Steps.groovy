package uk.gov.hmrc.jenkinsjobs.domain.step

import uk.gov.hmrc.jenkinsjobbuilders.domain.step.Step

import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.GradleStep.gradleStep
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.SbtStep.sbtStep
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep

class Steps {

    private Steps() {}

    static Step gradleCleanTestBuild() {
        gradleStep('$JAVA_PROXY_OPTS clean test build --info')
    }

    static Step gradleCleanTestPublish() {
        gradleStep('$JAVA_PROXY_OPTS -P releaseVersion="${TAG}" clean test bintrayUpload --info')
    }

    static Step sbtCleanTestPublish(String beforeTest = '', String afterTest = '') {
        sbtStep(["\$SBT_OPTS -mem 8192 clean validate ${beforeTest}test ${afterTest}+publishSigned"], '\${TMP}')
    }

    static Step sbtCleanTestFullOptJsPublish(String beforeTest = '', String afterTest = '') {
        sbtStep(["\$SBT_OPTS -mem 8192 clean validate ${beforeTest}test ${afterTest}fullOptJS universal:packageZipTarball +publishSigned"], '\${TMP}')
    }

    static Step sbtCleanTestItTestDistTgzPublish(String beforeTest, String afterTest) {
        sbtStep(["\$SBT_OPTS -mem 8192 clean validate ${beforeTest}test it:test ${afterTest}dist-tgz +publishSigned"], '\${TMP}')
    }

    //used by frontend
    static Step sbtCleanDistTgzPublish(String beforeTest, String tests, String afterTest, String bashScript = '') {
        sbtStep(bashScript, ["\$SBT_OPTS -mem 8192 clean validate $beforeTest$tests ${afterTest}dist-tgz +publishSigned"], '\${TMP}')
    }

    static Step gradleCleanDistTgzPublish() {
        gradleStep('$JAVA_PROXY_OPTS clean test bintrayUpload --info')
    }

    static Step cleanPublishSigned() {
        sbtStep(['\$SBT_OPTS -mem 8192 clean +publishSigned'], '\${TMP}')
    }

    static Step npmRelease(String nodeVersion) {
        shellStep("""
                  |set +x
                  |. \$NVM_DIR/nvm.sh
                  |nvm use $nodeVersion
                  |
                  |git config url.\"https://\".insteadOf \"git://\"
                  |
                  |npm install
                  |npm run release
                  |
                  |mkdir dist && cd dist
                  |
                  |for version in \$(find ../assets/public/* -maxdepth 0 -type d); do
                  |  npm pack \$version
                  |done
                  |
                  |cat >\${WORKSPACE}/.npmrc <<EOL
                  |registry=https://api.bintray.com/npm/hmrc/npm-release-candidates
                  |_auth=\${BINTRAY_CREDENTIALS}
                  |email=hmrc-build-and-deploy@digital.hmrc.gov.uk
                  |always-auth=true
                  |EOL
                  |
                  |for releaseCandidate in \$(find *.tgz); do
                  |  npm publish \$releaseCandidate
                  |done
                  """.stripMargin())
    }

    static Step createARelease() {
        shellStep("""\
                  |if [ ! -f "~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION/releaser_2.11-\$RELEASER_VERSION-assembly.jar" ]; then
                  |  mkdir -p ~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION
                  |  curl -L -k -o ~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION/releaser_2.11-\$RELEASER_VERSION-assembly.jar https://dl.bintray.com/hmrc/releases/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION/releaser_2.11-\$RELEASER_VERSION-assembly.jar
                  |fi
                  |function quotify () {  sed "s/\\"/\\\\\\"/g" <<< \$1; }
                  |java \$JAVA_PROXY_OPTS -Dwsclient.timeout.connection=300 -Dwsclient.timeout.idle=300 -Dwsclient.timeout.request=300 -jar ~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION/releaser_2.11-\$RELEASER_VERSION-assembly.jar \$ARTEFACT_NAME \$RELEASE_CANDIDATE_VERSION \$RELEASE_TYPE --release-notes "`quotify "\${RELEASE_NOTES}"`"
                  """.stripMargin())
    }

    static Step createAReleaseWithScalaVersion(String scalaVersion) {
        shellStep("""\
                  |if [ ! -f "~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION/releaser_2.11-\$RELEASER_VERSION-assembly.jar" ]; then
                  |  mkdir -p ~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION
                  |  curl -L -k -o ~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION/releaser_2.11-\$RELEASER_VERSION-assembly.jar https://dl.bintray.com/hmrc/releases/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION/releaser_2.11-\$RELEASER_VERSION-assembly.jar
                  |fi
                  |java \$JAVA_PROXY_OPTS -Dwsclient.timeout.connection=300 -Dwsclient.timeout.idle=300 -Dwsclient.timeout.request=300 -jar ~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION/releaser_2.11-\$RELEASER_VERSION-assembly.jar \$ARTEFACT_NAME \$RELEASE_CANDIDATE_VERSION \$RELEASE_TYPE --scalaversion $scalaVersion
                  """.stripMargin())
    }

    static Step dropMongoDatabase(String databaseName) {
        shellStep("""\
                  |mongo $databaseName --eval "db.dropDatabase()"
                  """.stripMargin())
    }
}
