package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.Job
import spock.lang.Specification
import uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable
import uk.gov.hmrc.jenkinsjobs.JobParents
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.JDK7

@Mixin(JobParents)
class SbtMicroserviceJobBuilderSpec extends Specification {

    void 'test XML output'() {
        given:
        SbtMicroserviceJobBuilder jobBuilder = new SbtMicroserviceJobBuilder('test-job')

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.url.text() == 'git@github.com:hmrc/test-job.git'
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('CLASSPATH')
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('JAVA_HOME')
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('PATH')
            triggers.'com.cloudbees.jenkins.gitHubPushTrigger'.spec.text() == ''
            builders.'hudson.tasks.Shell'.command.text().contains('sbt clean test it:test dist-tgz publishSigned')
            publishers.'hudson.tasks.junit.JUnitResultArchiver'.testResults.text() == 'target/*test-reports/*.xml'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[0].reportDir [0].text() == 'target/test-reports/html-report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[0].reportName [0].text() == 'HTML Report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[1].reportDir [0].text() == 'target/int-test-reports/html-report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[1].reportName [0].text() == 'IT HTML Report'
        }
    }

    void 'test XML output JDK7'() {
        given:
        SbtMicroserviceJobBuilder jobBuilder = new SbtMicroserviceJobBuilder('test-job', JDK7)

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('jdk1.7.0') == true
            builders.'hudson.tasks.Shell'.command.text().contains('sbt clean test it:test dist publishSigned')
        }
    }

    void 'test XML output with fun:tests'() {
        given:
        SbtMicroserviceJobBuilder jobBuilder = new SbtMicroserviceJobBuilder('test-job').withTests("test it:test fun:test")

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            builders.'hudson.tasks.Shell'.command.text().contains('sbt clean test it:test fun:test dist-tgz publishSigned')
        }
    }
}
