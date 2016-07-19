package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsconfig.jenkins.build.SbtFrontendJobBuilder

new SbtFrontendJobBuilder('ers-checking-frontend').
	withScalaStyle().
        withSCoverage().
        build(this as DslFactory)
