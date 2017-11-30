package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.Job
import spock.lang.Specification
import uk.gov.hmrc.jenkinsjobs.JobParents

import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.SbtStep.sbtStep
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep


@Mixin(JobParents)
class JourneyTestsWithZapJobBuilderSpec extends Specification {

    void 'Test XML output'() {
        given:
        JourneyTestsWithZapJobBuilder builder = new JourneyTestsWithZapJobBuilder(
                'test-job',
                'example/example-repo',
                shellStep("sbt \"func:test-only uk.gov.hmrc.ZapRunner\""))

        when:
        Job job = builder.build(jobParent())

        then:
        with(job.node) {
            builders.'hudson.tasks.Shell' [0].command.text().contains("sbt \"func:test-only uk.gov.hmrc.ZapRunner\"")
            publishers.'hudson.tasks.ArtifactArchiver' [0].artifacts.text().equals('results/zap/**')
        }
    }

}
