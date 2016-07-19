package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsconfig.jenkins.build.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsconfig.jenkins.build.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsconfig.jenkins.build.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsconfig.jenkins.build.SbtStubsJobBuilder
import uk.gov.hmrc.jenkinsconfig.jenkins.build.SplunkAppJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.ListViewBuilder


new SbtFrontendJobBuilder('ers-checking-frontend').
	withScalaStyle().
        withSCoverage().
        build(this)

new ListViewBuilder('ERS').
        withJobsRegex('ers.+').
        build(this)
