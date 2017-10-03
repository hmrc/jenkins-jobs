package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.Job
import spock.lang.Specification
import uk.gov.hmrc.jenkinsjobs.JobParents

@Mixin(JobParents)
class SmokeTestsJobBuilderSpec extends Specification {

    void 'test XML output'() {
        given:
        SmokeTestsJobBuilder jobBuilder = new SmokeTestsJobBuilder('test-job', 'example/example-repo', '-Dsbt.log.noformat=true smoke:test')

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            builders.'hudson.tasks.Shell' [0].command.text().contains('sbt -Dsbt.log.noformat=true smoke:test')
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'.reportName.text() == 'Smoke Test Report'
            publishers.'htmlpublisher.HtmlPublisher'.reportTargets.'htmlpublisher.HtmlPublisherTarget'.reportDir.text().contains('smoke-test-reports')
            buildWrappers.'hudson.plugins.build__timeout.BuildTimeoutWrapper'.strategy.timeoutMinutes.text() == '30'
        }
    }

    void 'test extended build timeout'() {
        given:
        SmokeTestsJobBuilder jobBuilder = new SmokeTestsJobBuilder('test-job', 'example/example-repo', '-Dsbt.log.noformat=true smoke:test')

        when:
        Job job = jobBuilder.withExtendedTimeout().build(jobParent())

        then:
        with(job.node) {
            buildWrappers.'hudson.plugins.build__timeout.BuildTimeoutWrapper'.strategy.timeoutMinutes.text() == '60'
        }
    }
}
