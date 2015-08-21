package uk.gov.hmrc.jenkinsjobs.domain.builder

import uk.gov.hmrc.jenkinsjobbuilders.domain.JobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable

import static java.util.Arrays.asList
import static uk.gov.hmrc.jenkinsjobbuilders.domain.scm.GitHubComScm.gitHubComScm
import static uk.gov.hmrc.jenkinsjobbuilders.domain.scm.PollScmTrigger.pollScmTrigger
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.ClasspathEnvironmentVariable.classpathEnvironmentVariable
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.JDK8
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.PathEnvironmentVariable.pathEnvironmentVariable

final class JobBuilders {

    private JobBuilders() {}

    static JobBuilder jobBuilder(String name, JdkEnvironmentVariable jdk = JDK8) {
        new JobBuilder(name, "${name} auto-configured job", 14, 10).
                       withEnvironmentVariables(environmentVariables(jdk))
    }

    static JobBuilder jobBuilder(String name, String repository, JdkEnvironmentVariable jdk = JDK8) {
        jobBuilder(name, jdk).
                   withScm(gitHubComScm(repository, 'ce814d36-5570-4f1f-ad70-0a8333122be6')).
                   withScmTriggers(pollScmTrigger("H/5 * * * *")).
                   withLabel('single-executor')
    }

    private static environmentVariables(JdkEnvironmentVariable jdk) {
        asList(jdk, classpathEnvironmentVariable(), pathEnvironmentVariable())
    }
}