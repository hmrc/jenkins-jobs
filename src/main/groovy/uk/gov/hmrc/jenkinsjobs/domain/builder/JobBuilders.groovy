package uk.gov.hmrc.jenkinsjobs.domain.builder

import uk.gov.hmrc.jenkinsjobbuilders.domain.JobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.variables.EnvironmentVariable
import uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable

import static java.util.Arrays.asList
import static uk.gov.hmrc.jenkinsjobbuilders.domain.scm.GitHubComScm.gitHubComScm
import static uk.gov.hmrc.jenkinsjobbuilders.domain.scm.PollScmTrigger.pollScmTrigger
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.jdk8EnvironmentVariable
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.PathEnvironmentVariable.pathEnvironmentVariable
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.ClasspathEnvironmentVariable.classpathEnvironmentVariable

final class JobBuilders {

    private JobBuilders() {}

    static JobBuilder jobBuilder(String name, JdkEnvironmentVariable jdkEnvironmentVariable = jdk8EnvironmentVariable()) {
        new JobBuilder(name, "${name} auto-configured job", 14, 10).
                       withEnvironmentVariables(jobEnvironmentVariables(jdkEnvironmentVariable))
    }

    static JobBuilder jobBuilder(String name, String repository, JdkEnvironmentVariable jdkEnvironmentVariable = jdk8EnvironmentVariable()) {
        jobBuilder(name, jdkEnvironmentVariable).
                   withScm(gitHubComScm(repository, 'ce814d36-5570-4f1f-ad70-0a8333122be6')).
                   withScmTriggers(pollScmTrigger("H/5 * * * *")).
                   withLabel('single-executor')
    }

    private static jobEnvironmentVariables(EnvironmentVariable ... environmentVariables) {
        List<EnvironmentVariable> jobEnvironmentVariables = new ArrayList(asList(classpathEnvironmentVariable(), pathEnvironmentVariable()))
        jobEnvironmentVariables.addAll(environmentVariables)
        jobEnvironmentVariables
    }
}