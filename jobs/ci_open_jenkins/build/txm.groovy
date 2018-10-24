package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtLibraryJobBuilder('http-verbs').
        build(this as DslFactory)

new SbtLibraryJobBuilder('auditing').
        build(this as DslFactory)

