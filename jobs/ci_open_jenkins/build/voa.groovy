package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtLibraryJobBuilder('play-conditional-form-mapping').
        build(this as DslFactory)

new SbtFrontendJobBuilder('for-frontend')
        .withScalaStyle()
        .build(this as DslFactory)