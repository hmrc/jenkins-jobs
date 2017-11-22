package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.JobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.step.Step

import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.JobsTriggerPublisher.jobsTriggerPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.step.ShellStep.shellStep
import static uk.gov.hmrc.jenkinsjobbuilders.domain.trigger.BintrayArtifactTrigger.bintrayArtifactTrigger
import static uk.gov.hmrc.jenkinsjobbuilders.domain.trigger.CronTrigger.cronTrigger
import static uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.AbsoluteTimeoutWrapper.timeoutWrapper
import static uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.NodeJsWrapper.nodeJsWrapper

class AbstractZapTestsJobBuilder<T extends AbstractZapTestsJobBuilder<T>> implements Builder<Job> {

    private boolean junitReports = true
    private int timeout = 60

    protected JobBuilder builder

    protected static Step startZap() {
        shellStep("""\
                    echo "Starting ZAP"
                    zap -daemon -config api.disablekey=true -port 11000 &
                    sleep 10
                    """.stripMargin())
    }

    protected static Step stopZap() {
        shellStep("""\
                  echo "Killing Zap"
                  curl --silent http://localhost:11000/HTML/core/action/shutdown
                  """.stripMargin())
    }

    T withDownstreamJobs(String downstreamJobs) {
        builder = builder.withPublishers(jobsTriggerPublisher(downstreamJobs))
        (T)this
    }

    T withLogRotator(int daysToKeep, int numToKeep) {
        builder = builder.withLogRotator(daysToKeep, numToKeep)
        (T)this
    }

    T withoutJUnitReports() {
        this.junitReports = false
        (T)this
    }

    T withBintrayArtifactTrigger(String cronSchedule, List<String> packagesToPoll) {
        builder = builder.withTriggers(bintrayArtifactTrigger(cronSchedule, "hmrc", "release-candidates", packagesToPoll))
        (T)this
    }

    T withCronTrigger(String cronExpression) {
        this.builder = builder.withTriggers(cronTrigger(cronExpression))
        (T)this
    }

    T withNodeJs(String version = '0.12.7') {
        builder = builder.withWrappers(nodeJsWrapper(version))
        (T)this
    }

    T withExtendedTimeout() {
        this.timeout = 120
        (T)this
    }

    T withDropMongoDatabase(String dbName) {
        this.builder = builder.withSteps(uk.gov.hmrc.jenkinsjobs.domain.step.Steps.dropMongoDatabase(dbName))
        (T)this
    }

    @Override
    Job build(DslFactory dslFactory) {
        if (junitReports) {
            builder = builder.withPublishers(uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.defaultJUnitReportsPublisher())
        }

        builder.withWrappers(timeoutWrapper(this.timeout)).build(dslFactory)
    }
}
