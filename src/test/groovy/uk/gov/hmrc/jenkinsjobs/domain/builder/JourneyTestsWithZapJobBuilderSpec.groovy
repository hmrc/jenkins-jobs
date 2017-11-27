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
                sbtStep(["-mem 8192 run"], '\${TMP}'),
                shellStep('./test-script-name.sh'),
                sbtStep(["-mem 8192 stop"], '\${TMP}'))

        when:
        Job job = builder.build(jobParent())

        then:
        with(job.node) {
            builders.'hudson.tasks.Shell' [0].command.text().contains('sbt -mem 8192 run')
            builders.'hudson.tasks.Shell' [1].command.text().contains('zap -daemon -config api.disablekey=true -port 11000 -dir $WORKSPACE/results/zap -newsession zap-journey &')
            builders.'hudson.tasks.Shell' [2].command.text().contains('./test-script-name.sh')
            builders.'hudson.tasks.Shell' [3].command.text().contains('curl --silent http://localhost:11000/HTML/core/action/shutdown')
            builders.'hudson.tasks.Shell' [4].command.text().contains('sbt -mem 8192 stop')
            publishers.'hudson.tasks.ArtifactArchiver' [0].artifacts.text().equals('results/zap/**')
        }
    }

}
