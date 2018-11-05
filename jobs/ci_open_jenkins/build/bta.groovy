package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtFrontendJobBuilder('help-frontend').
        withXvfb().
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-ui-23', 'play-ui', 'play-23').
        build(this as DslFactory)


