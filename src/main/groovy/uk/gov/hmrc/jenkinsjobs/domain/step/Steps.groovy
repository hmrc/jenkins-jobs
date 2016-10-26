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
        sbtStep(["\$SBT_OPTS clean validate ${beforeTest}test ${afterTest}publishSigned"], '\${TMPDIR}')
    }
    
    static Step sbtCleanTestFullOptJsPublish(String beforeTest = '', String afterTest = '') {
        sbtStep(["\$SBT_OPTS clean validate ${beforeTest}test ${afterTest}fullOptJS universal:packageXzTarball publishSigned"], '\${TMPDIR}')
    }

    static Step sbtCleanTestItTestDistTgzPublish(String beforeTest, String afterTest) {
        sbtStep(["\$SBT_OPTS clean validate ${beforeTest}test it:test ${afterTest}dist-tgz publishSigned"], '\${TMPDIR}')
    }

    static Step sbtCleanDistTgzPublish(String beforeTest, String tests, String afterTest) {
        sbtStep(["\$SBT_OPTS clean validate $beforeTest$tests ${afterTest}dist-tgz publishSigned"], '\${TMPDIR}')
    }

    static Step gradleCleanDistTgzPublish() {
        gradleStep('$JAVA_PROXY_OPTS clean test bintrayUpload --info')
    }

    static Step cleanPublishSigned() {
        sbtStep(['\$SBT_OPTS clean publishSigned'], '\${TMPDIR}')
    }

    static Step createARelease() {
        shellStep("""\
                  |if [ ! -f "~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION/releaser_2.11-\$RELEASER_VERSION-assembly.jar" ]; then
                  |  mkdir -p ~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION
                  |  curl -L -k -o ~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION/releaser_2.11-\$RELEASER_VERSION-assembly.jar https://dl.bintray.com/hmrc/releases/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION/releaser_2.11-\$RELEASER_VERSION-assembly.jar
                  |fi
                  |java \$JAVA_PROXY_OPTS -Dwsclient.timeout.connection=300 -Dwsclient.timeout.idle=300 -Dwsclient.timeout.request=300 -jar ~/.m2/repository/uk/gov/hmrc/releaser_2.11/\$RELEASER_VERSION/releaser_2.11-\$RELEASER_VERSION-assembly.jar \$ARTEFACT_NAME \$RELEASE_CANDIDATE_VERSION \$RELEASE_TYPE
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


    static Step createARepository(String repositoryName, String teamName, String repositoryType, String bootStrapTag, String enableTravis) {
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
                  |java \$JAVA_PROXY_OPTS -jar ~/.m2/repository/uk/gov/hmrc/init-repository_2.11/\$INIT_REPO_VERSION/init-repository_2.11-\$INIT_REPO_VERSION-assembly.jar "$repositoryName" "$teamName" "$repositoryType" "$bootStrapTag" \$enable_travis
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
