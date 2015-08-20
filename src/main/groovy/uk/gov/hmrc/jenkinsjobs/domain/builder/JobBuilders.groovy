package uk.gov.hmrc.jenkinsjobs.domain.builder

import uk.gov.hmrc.jenkinsjobbuilders.domain.JobBuilder

import static java.util.Collections.emptyMap
import static uk.gov.hmrc.jenkinsjobbuilders.domain.scm.GitHubComScm.gitHubComScm
import static uk.gov.hmrc.jenkinsjobbuilders.domain.scm.PollScmTrigger.pollScmTrigger
import static uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.EnvironmentVariablesWrapper.environmentVariablesWrapper

final class JobBuilders {

    private JobBuilders() {}

    static JobBuilder jobBuilder(String name, Map<String, String> environmentVariables = emptyMap()) {
        newJobBuilder(name, environmentVariables)
    }

    static JobBuilder jobBuilder(String name, String repository) {
        newJobBuilder(name).
                      withScm(gitHubComScm(repository, 'ce814d36-5570-4f1f-ad70-0a8333122be6')).
                      withScmTriggers(pollScmTrigger("H/5 * * * *")).
                      withLabel('single-executor')
    }

    private static JobBuilder newJobBuilder(String name, Map<String, String> environmentVariables = emptyMap()) {
        new JobBuilder(name, "${name} auto-configured job", 14, 10).
                       withWrappers(environmentVariablesWrapper(jobEnvironmentVariables(environmentVariables)))
    }

    private static jobEnvironmentVariables(Map<String, String> environmentVariables) {
        Map<String, String> jobEnvironmentVariables = [CLASSPATH: '${CLASSPATH}:/opt/sbt/bin',
                                                       JAVA_HOME: '/usr/lib/jvm/jdk1.7.0_51',
                                                       PATH: '$JAVA_HOME/bin:/opt/sbt/bin:$PATH']
        jobEnvironmentVariables.putAll(environmentVariables)
        jobEnvironmentVariables
    }
}
