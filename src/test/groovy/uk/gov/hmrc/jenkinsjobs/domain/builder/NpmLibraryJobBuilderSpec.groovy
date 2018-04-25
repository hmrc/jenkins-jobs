package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.Job
import spock.lang.Specification
import uk.gov.hmrc.jenkinsjobs.JobParents

@Mixin(JobParents)
class NpmLibraryJobBuilderSpec extends Specification {

    void 'test XML output'() {
        given:
        NpmLibraryJobBuilder jobBuilder = new NpmLibraryJobBuilder('test-job')

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.url.text() == 'git@github.com:hmrc/test-job.git'
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('CLASSPATH')
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('JAVA_HOME')
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('PATH')
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('TMP')
            triggers.'com.cloudbees.jenkins.gitHubPushTrigger'.spec.text() == ''
            buildWrappers.'hudson.plugins.build__timeout.BuildTimeoutWrapper'.strategy.timeoutMinutes.text() == '15'
            builders.'hudson.tasks.Shell'.command.text().startsWith("""
                                                                    |set +x
                                                                    |. \$NVM_DIR/nvm.sh
                                                                    |nvm use 4.8.4""".stripMargin())
            builders.'hudson.tasks.Shell'.command.text().contains('npm install')
            builders.'hudson.tasks.Shell'.command.text().contains('npm run release')
            builders.'hudson.tasks.Shell'.command.text().contains('npm pack')
            builders.'hudson.tasks.Shell'.command.text().contains('npm publish')
        }
    }

    void 'test nodejs configuration with specified version'() {
        given:
        final String nodeVersion = "0.12.7"
        NpmLibraryJobBuilder jobBuilder = new NpmLibraryJobBuilder('test-job').withNodeJsVersion(nodeVersion)

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            builders.'hudson.tasks.Shell'.command.text().startsWith("""
                                                                    |set +x
                                                                    |. \$NVM_DIR/nvm.sh
                                                                    |nvm use ${nodeVersion}""".stripMargin())
        }
    }
}
