package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.Job
import spock.lang.Specification
import uk.gov.hmrc.jenkinsjobs.JobParents

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
            builders.'hudson.tasks.Shell'.command.text().contains('sbt $SBT_OPTS clean validate test it:test dist-tgz publishSigned')
            publishers.'hudson.tasks.junit.JUnitResultArchiver'.testResults.text() == 'target/*test-reports/*.xml'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[0].reportDir [0].text() == 'target/test-reports/html-report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[0].reportName [0].text() == 'HTML Report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[1].reportDir [0].text() == 'target/int-test-reports/html-report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[1].reportName [0].text() == 'IT HTML Report'
            buildWrappers.'hudson.plugins.build__timeout.BuildTimeoutWrapper'.strategy.timeoutMinutes.text() == '15'
        }
    }

    void 'test XML output with fun:tests'() {
        given:
        SbtMicroserviceJobBuilder jobBuilder = new SbtMicroserviceJobBuilder('test-job').withTests("test it:test fun:test")

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            builders.'hudson.tasks.Shell'.command.text().contains('sbt $SBT_OPTS clean validate test it:test fun:test dist-tgz publishSigned')
        }
    }

    void 'test XML output with additional publisher'() {
        given:
        SbtMicroserviceJobBuilder jobBuilder = new SbtMicroserviceJobBuilder('test-job').withHtmlReports('target/fun-test-reports/html-report': 'FUN HTML Report')

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[0].reportDir [0].text() == 'target/test-reports/html-report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[0].reportName [0].text() == 'HTML Report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[1].reportDir [0].text() == 'target/int-test-reports/html-report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[1].reportName [0].text() == 'IT HTML Report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[2].reportDir [0].text() == 'target/fun-test-reports/html-report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[2].reportName [0].text() == 'FUN HTML Report'
        }
    }

    void 'test scoverage output'() {
        given:
        SbtMicroserviceJobBuilder jobBuilder = new SbtMicroserviceJobBuilder('test-job').withSCoverage().withTests("test")

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            builders.'hudson.tasks.Shell'.command.text().contains('sbt $SBT_OPTS clean validate coverage test coverageOff dist-tgz publishSigned')
            publishers.'org.jenkinsci.plugins.scoverage.ScoveragePublisher'.reportDir.text() == "target/scala-2.11/scoverage-report"
            publishers.'org.jenkinsci.plugins.scoverage.ScoveragePublisher'.reportFile.text() == "scoverage.xml"
        }
    }

    void 'test scalastyle output'() {
        given:
        SbtMicroserviceJobBuilder jobBuilder = new SbtMicroserviceJobBuilder('test-job').withScalaStyle().withTests("test")

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            builders.'hudson.tasks.Shell'.command.text().contains('sbt $SBT_OPTS clean validate scalastyle test dist-tgz publishSigned')
            publishers.'hudson.plugins.checkstyle.CheckStylePublisher'.pluginName.text() == "[CHECKSTYLE]"
        }
    }

    void 'test scoverage and scalastyle output'() {
        given:
        SbtMicroserviceJobBuilder jobBuilder = new SbtMicroserviceJobBuilder('test-job').withScalaStyle().withSCoverage().withTests("test")

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            builders.'hudson.tasks.Shell'.command.text().contains('sbt $SBT_OPTS clean validate scalastyle coverage test coverageOff dist-tgz publishSigned')
            publishers.'org.jenkinsci.plugins.scoverage.ScoveragePublisher'.reportDir.text() == "target/scala-2.11/scoverage-report"
            publishers.'org.jenkinsci.plugins.scoverage.ScoveragePublisher'.reportFile.text() == "scoverage.xml"
            publishers.'hudson.plugins.checkstyle.CheckStylePublisher'.pluginName.text() == "[CHECKSTYLE]"
        }
    }

    void 'test extended build timeout'() {
        given:
        SbtMicroserviceJobBuilder jobBuilder = new SbtMicroserviceJobBuilder('test-job')

        when:
        Job job = jobBuilder.withExtendedTimeout().build(jobParent())

        then:
        with(job.node) {
            buildWrappers.'hudson.plugins.build__timeout.BuildTimeoutWrapper'.strategy.timeoutMinutes.text() == '30'
        }
    }
}
