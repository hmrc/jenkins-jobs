package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.Job
import spock.lang.Specification
import uk.gov.hmrc.jenkinsjobs.JobParents

import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.SbtStep.sbtStep
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep

@Mixin(JobParents)
class ZapTestsFollowingJourneyJobBuilderSpec extends Specification {

    void 'Test XML output'() {
        given:
        ZapTestsFollowingJourneyJobBuilder builder = new ZapTestsFollowingJourneyJobBuilder(
                'test-job',
                'example/example-repo',
                sbtStep(["-mem 8192 run"], '\${TMP}'),
                shellStep('./test-script-name.sh'),
                sbtStep(["-mem 8192 stop"], '\${TMP}'),
                'upstream-job')

        when:
        Job job = builder.build(jobParent())

        then:
        System.out.println(job.xml)
        with(job.node) {
            builders.'hudson.tasks.Shell' [0].command.text().contains('-mem 8192 run')
            builders.'hudson.plugins.copyartifact.CopyArtifact' [0].project.text().equals('upstream-job')
            builders.'hudson.plugins.copyartifact.CopyArtifact' [0].filter.text().equals('results/zap/**')
            builders.'hudson.tasks.Shell' [1].command.text().contains('BUILD_ID=dontKillMe zap -daemon -config api.disablekey=true -port 11000 -dir $WORKSPACE/results/zap -session zap-journey &')
            builders.'hudson.tasks.Shell' [2].command.text().contains('./test-script-name.sh')
            builders.'hudson.tasks.Shell' [3].command.text().contains('curl --silent http://localhost:11000/HTML/core/action/shutdown')
            builders.'hudson.tasks.Shell' [4].command.text().contains('-mem 8192 stop')
        }
   }
}
