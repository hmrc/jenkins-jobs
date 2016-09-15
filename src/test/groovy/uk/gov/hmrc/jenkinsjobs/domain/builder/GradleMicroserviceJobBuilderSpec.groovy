package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.Job
import spock.lang.Specification
import uk.gov.hmrc.jenkinsjobs.JobParents

@Mixin(JobParents)
class GradleMicroserviceJobBuilderSpec extends Specification {

    void 'test XML output'() {
        given:
        GradleMicroserviceJobBuilder jobBuilder = new GradleMicroserviceJobBuilder('test-job')

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.url.text() == 'git@github.com:hmrc/test-job.git'
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('CLASSPATH')
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('JAVA_HOME')
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('PATH')
            triggers.'com.cloudbees.jenkins.gitHubPushTrigger'.spec.text() == ''
            //TODO - builders.'hudson.tasks.Shell'.command.text().contains('sbt $SBT_OPTS clean validate test it:test dist-tgz publishSigned')
            publishers.'hudson.tasks.junit.JUnitResultArchiver'.testResults.text() == 'build/test-results/*.xml'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[0].reportDir [0].text() == 'build/reports/tests/*html'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[0].reportName [0].text() == 'HTML Report'

            buildWrappers.'hudson.plugins.build__timeout.BuildTimeoutWrapper'.strategy.timeoutMinutes.text() == '15'
        }
    }

    void 'test extended build timeout'() {
        given:
        GradleMicroserviceJobBuilder jobBuilder = new GradleMicroserviceJobBuilder('test-job')

        when:
        Job job = jobBuilder.withExtendedTimeout().build(jobParent())

        then:
        with(job.node) {
            buildWrappers.'hudson.plugins.build__timeout.BuildTimeoutWrapper'.strategy.timeoutMinutes.text() == '30'
        }
    }
}
