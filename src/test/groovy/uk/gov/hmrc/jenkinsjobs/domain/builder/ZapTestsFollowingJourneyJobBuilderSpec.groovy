package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.Job
import spock.lang.Specification
import uk.gov.hmrc.jenkinsjobs.JobParents

import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep as shellStep

@Mixin(JobParents)
class ZapTestsFollowingJourneyJobBuilderSpec extends Specification {

    void 'Test XML output'() {
        given:
        ZapTestsFollowingJourneyJobBuilder builder = new ZapTestsFollowingJourneyJobBuilder(
                'test-job',
                'example/example-repo',
                shellStep("sbt \"func:test-only uk.gov.hmrc.ZapRunner\""),
                'upstream-job')

        when:
        Job job = builder.build(jobParent())

        then:
        System.out.println(job.xml)
        with(job.node) {
            builders.'hudson.plugins.copyartifact.CopyArtifact' [0].project.text().equals('upstream-job')
            builders.'hudson.plugins.copyartifact.CopyArtifact' [0].filter.text().equals('results/zap/**')
            builders.'hudson.tasks.Shell' [0].command.text().contains("zap -daemon -config api.disablekey=true -port 11000")
            builders.'hudson.tasks.Shell' [1].command.text().contains("sbt \"func:test-only uk.gov.hmrc.ZapRunner\"")
            builders.'hudson.tasks.Shell' [2].command.text().contains("curl --silent http://localhost:11000/HTML/core/action/shutdown")
        }
   }
}
