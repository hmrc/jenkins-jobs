package uk.gov.hmrc.jenkinsjobs.domain

import javaposse.jobdsl.dsl.Job
import spock.lang.Specification
import uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable
import uk.gov.hmrc.jenkinsjobs.JobParents
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.jdk7EnvironmentVariable

@Mixin(JobParents)
class SbtFrontendJobBuilderSpec extends Specification {

    void 'test XML output'() {
        given:
        SbtFrontendJobBuilder jobBuilder = new SbtFrontendJobBuilder('test-job', jdk7EnvironmentVariable())

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.url.text() == 'git@github.com:hmrc/test-job.git'
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('CLASSPATH') == true
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('JAVA_HOME') == true
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('PATH') == true
            triggers.'com.cloudbees.jenkins.gitHubPushTrigger'.spec.text() == ''
            builders.'hudson.tasks.Shell'.command.text().contains('sbt clean test it:test dist-tgz publishSigned')
            publishers.'hudson.tasks.junit.JUnitResultArchiver'.testResults.text() == 'target/*test-reports/*.xml'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[0].reportDir [0].text() == 'target/test-reports/html-report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[0].reportName [0].text() == 'HTML Report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[1].reportDir [0].text() == 'target/int-test-reports/html-report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[1].reportName [0].text() == 'IT HTML Report'
        }
    }
}
