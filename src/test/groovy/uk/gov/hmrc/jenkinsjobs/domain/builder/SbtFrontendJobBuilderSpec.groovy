package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.Job
import spock.lang.Specification
import uk.gov.hmrc.jenkinsjobs.JobParents

@Mixin(JobParents)
class SbtFrontendJobBuilderSpec extends Specification {

    void 'test XML output'() {
        given:
        SbtFrontendJobBuilder jobBuilder = new SbtFrontendJobBuilder('test-job')

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.url.text() == 'git@github.com:hmrc/test-job.git'
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('CLASSPATH')
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('JAVA_HOME')
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('PATH')
            triggers.'com.cloudbees.jenkins.gitHubPushTrigger'.spec.text() == ''
            builders.'hudson.tasks.Shell'.command.text().contains('sbt clean validate test it:test dist-tgz publishSigned')
            publishers.'hudson.tasks.junit.JUnitResultArchiver'.testResults.text() == 'target/*test-reports/*.xml'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[0].reportDir [0].text() == 'target/test-reports/html-report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[0].reportName [0].text() == 'HTML Report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[1].reportDir [0].text() == 'target/int-test-reports/html-report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[1].reportName [0].text() == 'IT HTML Report'
        }
    }

    void 'test scoverage output'() {
        given:
        SbtFrontendJobBuilder jobBuilder = new SbtFrontendJobBuilder('test-job').withSCoverage()

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            builders.'hudson.tasks.Shell'.command.text().contains('sbt clean validate coverage test it:test coverageOff dist-tgz publishSigned')
            publishers.'org.jenkinsci.plugins.scoverage.ScoveragePublisher'.reportDir.text() == "target/scala-2.11/scoverage-report"
            publishers.'org.jenkinsci.plugins.scoverage.ScoveragePublisher'.reportFile.text() == "scoverage.xml"
        }
    }

    void 'test scalastyle output'() {
        given:
        SbtFrontendJobBuilder jobBuilder = new SbtFrontendJobBuilder('test-job').withScalaStyle()

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            builders.'hudson.tasks.Shell'.command.text().contains('sbt clean validate scalastyle test it:test dist-tgz publishSigned')
            publishers.'hudson.plugins.checkstyle.CheckStylePublisher'.pluginName.text() == "[CHECKSTYLE]"
        }
    }

    void 'test scoverage and scalastyle output'() {
        given:
        SbtFrontendJobBuilder jobBuilder = new SbtFrontendJobBuilder('test-job').withScalaStyle().withSCoverage()

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            builders.'hudson.tasks.Shell'.command.text().contains('sbt clean validate scalastyle coverage test it:test coverageOff dist-tgz publishSigned')
            publishers.'org.jenkinsci.plugins.scoverage.ScoveragePublisher'.reportDir.text() == "target/scala-2.11/scoverage-report"
            publishers.'org.jenkinsci.plugins.scoverage.ScoveragePublisher'.reportFile.text() == "scoverage.xml"
            publishers.'hudson.plugins.checkstyle.CheckStylePublisher'.pluginName.text() == "[CHECKSTYLE]"
        }
    }

    void 'test XML output with fun:tests'() {
        given:
        SbtFrontendJobBuilder jobBuilder = new SbtFrontendJobBuilder('test-job').withTests("test acceptance:test")

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            builders.'hudson.tasks.Shell'.command.text().contains('sbt clean validate test acceptance:test dist-tgz publishSigned')
        }
    }

    void 'test XML output with additional publisher'() {
        given:
        SbtFrontendJobBuilder jobBuilder = new SbtFrontendJobBuilder('test-job').withHtmlReports('target/acceptance-test-reports/html-report': 'Acceptance HTML Report')

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[0].reportDir [0].text() == 'target/test-reports/html-report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[0].reportName [0].text() == 'HTML Report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[1].reportDir [0].text() == 'target/int-test-reports/html-report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[1].reportName [0].text() == 'IT HTML Report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[2].reportDir [0].text() == 'target/acceptance-test-reports/html-report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'[2].reportName [0].text() == 'Acceptance HTML Report'
        }
    }

}
