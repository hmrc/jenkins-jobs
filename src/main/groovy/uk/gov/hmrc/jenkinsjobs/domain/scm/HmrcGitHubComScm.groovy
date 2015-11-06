package uk.gov.hmrc.jenkinsjobs.domain.scm

import uk.gov.hmrc.jenkinsjobbuilders.domain.scm.Scm

import static uk.gov.hmrc.jenkinsjobbuilders.domain.scm.GitHubComScm.gitHubComScm


class HmrcGitHubComScm implements Scm {
    private static final String JENKINS_UUID = 'ce814d36-5570-4f1f-ad70-0a8333122be6'
    private final Scm scm

    private HmrcGitHubComScm(String name, String branch) {
        scm = gitHubComScm("hmrc/$name", JENKINS_UUID, branch)
    }

    static Scm hmrcGitHubComScm(String name, String branch = 'master') {
        new HmrcGitHubComScm(name, branch)
    }

    @Override
    Closure toDsl() {
        scm.toDsl()
    }
}
