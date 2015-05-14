package uk.gov.hmrc.jenkinsjobs.domain.builder

import uk.gov.hmrc.jenkinsjobbuilders.domain.JobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.scm.GitHubComScm.gitHubComScm


final class JobBuilders {

    private JobBuilders() {}

    static JobBuilder jobBuilders(String name1, String repository) {
        new JobBuilder(name1, "${name1} auto-configured job", 14, 10, gitHubComScm(repository))
    }
}
