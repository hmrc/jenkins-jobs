package uk.gov.hmrc.jenkinsjobs.domain.builder

import uk.gov.hmrc.jenkinsjobbuilders.domain.JobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.scm.GitHubComScm.gitHubComScm

final class JobBuilders {

    private JobBuilders() {}

    static JobBuilder jobBuilders(String name, String repository) {
        new JobBuilder(name, "${name} auto-configured job", 14, 10, gitHubComScm(repository, 'ce814d36-5570-4f1f-ad70-0a8333122be6'))
    }
}
