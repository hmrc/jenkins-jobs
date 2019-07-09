package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtLibraryJobBuilder('play-conditional-form-mapping').build(this as DslFactory)

new SbtLibraryJobBuilder('uri-template').withSCoverage().build(this as DslFactory)
