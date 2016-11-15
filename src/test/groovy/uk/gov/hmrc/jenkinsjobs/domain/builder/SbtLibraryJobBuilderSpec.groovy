package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.Job
import spock.lang.Specification
import uk.gov.hmrc.jenkinsjobs.JobParents

@Mixin(JobParents)
class SbtLibraryJobBuilderSpec extends Specification {

    void 'test XML output'() {
        given:
        SbtLibraryJobBuilder jobBuilder = new SbtLibraryJobBuilder('test-job')

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        println job.node
        with(job.node) {
            scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.url.text() == 'git@github.com:hmrc/test-job.git'
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('CLASSPATH')
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('JAVA_HOME')
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('PATH')
            triggers.'com.cloudbees.jenkins.gitHubPushTrigger'.spec.text() == ''
            builders.'hudson.tasks.Shell'.command.text().contains('sbt $SBT_OPTS clean validate test publishSigned')
            publishers.'hudson.tasks.junit.JUnitResultArchiver'.testResults.text() == 'target/*test-reports/*.xml'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'.reportDir [0].text() == 'target/test-reports/html-report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'.reportName [0].text() == 'HTML Report'
            buildWrappers.'hudson.plugins.build__timeout.BuildTimeoutWrapper'.strategy.timeoutMinutes.text() == '10'
        }
    }

    void 'test scoverage output'() {
        given:
        SbtLibraryJobBuilder jobBuilder = new SbtLibraryJobBuilder('test-job').withSCoverage()

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            builders.'hudson.tasks.Shell'.command.text().contains('sbt $SBT_OPTS clean validate coverage test coverageOff coverageReport publishSigned')
            publishers.'org.jenkinsci.plugins.scoverage.ScoveragePublisher'.reportDir.text() == "target/scala-2.11/scoverage-report"
            publishers.'org.jenkinsci.plugins.scoverage.ScoveragePublisher'.reportFile.text() == "scoverage.xml"
        }
    }

    void 'test extended build timeout'() {
        given:
        SbtLibraryJobBuilder jobBuilder = new SbtLibraryJobBuilder('test-job')

        when:
        Job job = jobBuilder.withExtendedTimeout().build(jobParent())

        then:
        println job.node
        with(job.node) {
            buildWrappers.'hudson.plugins.build__timeout.BuildTimeoutWrapper'.strategy.timeoutMinutes.text() == '20'
        }
    }

    void 'test cron trigger set'() {
        given:
        SbtLibraryJobBuilder jobBuilder = new SbtLibraryJobBuilder('test-job')
                .withCronTrigger('test-cron-trigger')

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            triggers.'hudson.triggers.TimerTrigger'.spec.text() == 'test-cron-trigger'
        }
    }
}
