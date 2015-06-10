package uk.gov.hmrc.jenkinsjobs.domain.builder

import uk.gov.hmrc.jenkinsjobbuilders.domain.JobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.scm.Scm

import static uk.gov.hmrc.jenkinsjobbuilders.domain.scm.GitHubComScm.gitHubComScm
import static uk.gov.hmrc.jenkinsjobbuilders.domain.scm.PollScmTrigger.pollScmTrigger

final class JobBuilders {

    private JobBuilders() {}

    static JobBuilder jobBuilder(String name) {
        newJobBuilder(name)
    }

    static JobBuilder jobBuilder(String name, String repository) {
        newJobBuilder(name).
                      withScm(gitHubComScm(repository, 'ce814d36-5570-4f1f-ad70-0a8333122be6')).
                      withScmTriggers(pollScmTrigger("H/5 * * * *")).
                      withLabel('single-executor')
    }

    private static JobBuilder newJobBuilder(String name) {
        new JobBuilder(name, "${name} auto-configured job", 14, 10)
    }
}
