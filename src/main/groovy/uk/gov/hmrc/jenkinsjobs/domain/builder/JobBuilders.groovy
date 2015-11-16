package uk.gov.hmrc.jenkinsjobs.domain.builder

import uk.gov.hmrc.jenkinsjobbuilders.domain.JobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable

import static uk.gov.hmrc.jenkinsjobbuilders.domain.scm.PollScmTrigger.pollScmTrigger
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.ClasspathEnvironmentVariable.classpathEnvironmentVariable
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.JDK8
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.PathEnvironmentVariable.pathEnvironmentVariable
import static uk.gov.hmrc.jenkinsjobs.domain.scm.HmrcGitHubComScm.hmrcGitHubComScm

final class JobBuilders {

    private JobBuilders() {}

    static JobBuilder jobBuilder(String name) {
        new JobBuilder(name, "${name} auto-configured job").
                       withLogRotator(14, 10).
                       withEnvironmentVariables(environmentVariables(JDK8))
    }

    static JobBuilder jobBuilder(String name, String repository) {
        jobBuilder(name, repository, 'master')
    }

    static JobBuilder jobBuilder(String name, String repository, String branch) {
        jobBuilder(name).
                   withScm(hmrcGitHubComScm(repository, branch)).
                   withScmTriggers(pollScmTrigger("H/5 * * * *")).
                   withLabel('single-executor')
    }

    private static environmentVariables(JdkEnvironmentVariable jdk) {
        [jdk, classpathEnvironmentVariable(), pathEnvironmentVariable(jdk)]
    }
}