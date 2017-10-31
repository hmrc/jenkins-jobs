package uk.gov.hmrc.jenkinsjobs.domain.step

import uk.gov.hmrc.jenkinsjobbuilders.domain.step.Step

import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.GradleStep.gradleStep
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.SbtStep.sbtStep
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep

class Steps {

    private Steps() {}

    static Step gradleCleanTestPublish() {
        gradleStep('$JAVA_PROXY_OPTS clean test bintrayUpload --info')
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

    static Step createAJavaRelease() {
        shellStep("""\
                  |if [ ! -f "~/.m2/repository/uk/gov/hmrc/java-releaser-poc_2.11/\$RELEASER_VERSION/java-releaser-poc_2.11-\$RELEASER_VERSION-assembly.jar" ]; then
                  |  mkdir -p ~/.m2/repository/uk/gov/hmrc/java-releaser-poc_2.11/\$RELEASER_VERSION
                  |  curl -L -k -o ~/.m2/repository/uk/gov/hmrc/java-releaser-poc_2.11/\$RELEASER_VERSION/java-releaser-poc_2.11-\$RELEASER_VERSION-assembly.jar https://dl.bintray.com/hmrc/releases/uk/gov/hmrc/java-releaser-poc_2.11/\$RELEASER_VERSION/java-releaser-poc_2.11-\$RELEASER_VERSION-assembly.jar
                  |fi
                  |java \$JAVA_PROXY_OPTS -Dwsclient.timeout.connection=300 -Dwsclient.timeout.idle=300 -Dwsclient.timeout.request=300 -jar ~/.m2/repository/uk/gov/hmrc/java-releaser-poc_2.11/\$RELEASER_VERSION/java-releaser-poc_2.11-\$RELEASER_VERSION-assembly.jar \$ARTEFACT_NAME \$RELEASE_CANDIDATE_VERSION \$RELEASE_TYPE
                  """.stripMargin())
    }


    static Step createARepository(String repositoryName, String teamName, String repositoryType, String bootStrapTag, String enableTravis, String digitalServiceName) {
        shellStep("""\
                  |if [ ! -f "~/.m2/repository/uk/gov/hmrc/init-repository_2.11/\$INIT_REPO_VERSION/init-repository_2.11-\$INIT_REPO_VERSION-assembly.jar" ]; then
                  |  mkdir -p ~/.m2/repository/uk/gov/hmrc/init-repository_2.11/\$INIT_REPO_VERSION
                  |  curl -L -k -o ~/.m2/repository/uk/gov/hmrc/init-repository_2.11/\$INIT_REPO_VERSION/init-repository_2.11-\$INIT_REPO_VERSION-assembly.jar https://dl.bintray.com/hmrc/releases/uk/gov/hmrc/init-repository_2.11/\$INIT_REPO_VERSION/init-repository_2.11-\$INIT_REPO_VERSION-assembly.jar
                  |fi
                  |enable_travis=""
                  |#if [ $enableTravis = true ]; then
                  | #enable_travis="--enable-travis"
                  |#fi
                  |
                  |
                  |DIGITAL_SERVICE=$digitalServiceName
                  |DIGITAL_SERVICE_NAME_COMMAND=""
                  |if [ -n "\$DIGITAL_SERVICE" ]; then
                  |  DIGITAL_SERVICE_NAME_COMMAND="-dsn $digitalServiceName";
                  |else
                  |   echo "digital service name is empty. Therefor the manifest file will not be created";
                  |fi
                  |
                  |java \$JAVA_PROXY_OPTS -jar ~/.m2/repository/uk/gov/hmrc/init-repository_2.11/\$INIT_REPO_VERSION/init-repository_2.11-\$INIT_REPO_VERSION-assembly.jar "$repositoryName" "$teamName" "$repositoryType" "$bootStrapTag" \$enable_travis \$DIGITAL_SERVICE_NAME_COMMAND
                  """.stripMargin())
    }

    static Step createAWebhook(String credFilePath, String apiBase, String repoOrg, String repositoryNames, String webhookUrl, String events) {
        shellStep("""\
                  |if [ ! -f "~/.m2/repository/uk/gov/hmrc/init-webhook_2.11/\$INIT_WEBHOOK_VERSION/init-webhook_2.11-\$INIT_WEBHOOK_VERSION-assembly.jar" ]; then
                  |  mkdir -p ~/.m2/repository/uk/gov/hmrc/init-webhook_2.11/\$INIT_WEBHOOK_VERSION
                  |  curl -L -k -o ~/.m2/repository/uk/gov/hmrc/init-webhook_2.11/\$INIT_WEBHOOK_VERSION/init-webhook_2.11-\$INIT_WEBHOOK_VERSION-assembly.jar https://dl.bintray.com/hmrc/releases/uk/gov/hmrc/init-webhook_2.11/\$INIT_WEBHOOK_VERSION/init-webhook_2.11-\$INIT_WEBHOOK_VERSION-assembly.jar
                  |fi
                  |java \$JAVA_PROXY_OPTS -jar ~/.m2/repository/uk/gov/hmrc/init-webhook_2.11/\$INIT_WEBHOOK_VERSION/init-webhook_2.11-\$INIT_WEBHOOK_VERSION-assembly.jar "-cf" "$credFilePath" "-h" "$apiBase" "-o" "$repoOrg" "-rn" "$repositoryNames" "-wu" "$webhookUrl" "-e" "$events"
                  """.stripMargin())
    }
}
