package uk.gov.hmrc.jenkinsjobs.domain.builder

import uk.gov.hmrc.jenkinsjobbuilders.domain.JobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable

import static java.util.Arrays.asList
import static uk.gov.hmrc.jenkinsjobbuilders.domain.scm.PollScmTrigger.pollScmTrigger
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.ClasspathEnvironmentVariable.classpathEnvironmentVariable
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.JDK8
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.PathEnvironmentVariable.pathEnvironmentVariable
import static uk.gov.hmrc.jenkinsjobs.domain.scm.HmrcGitHubComScm.hmrcGitHubComScm

final class JobBuilders {

    private JobBuilders() {}

    static JobBuilder jobBuilder(String name, JdkEnvironmentVariable jdk = JDK8) {
        new JobBuilder(name, "${name} auto-configured job").
                       withLogRotator(14, 10).
                       withEnvironmentVariables(environmentVariables(jdk))
    }

    static JobBuilder jobBuilder(String name, String repository, JdkEnvironmentVariable jdk = JDK8) {
        jobBuilder(name, jdk).
                   withScm(hmrcGitHubComScm(repository)).
                   withScmTriggers(pollScmTrigger("H/5 * * * *")).
                   withLabel('single-executor')
    }

    private static environmentVariables(JdkEnvironmentVariable jdk) {
        asList(jdk, classpathEnvironmentVariable(), pathEnvironmentVariable())
    }
}