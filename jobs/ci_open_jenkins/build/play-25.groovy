package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

//play 2.5 jobs
new SbtLibraryJobBuilder('http-verbs-25').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-auditing-25').
        build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-distributables-25').
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('sbt-distributables-plugin-25').
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('play-health-25').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-json-logger-25').
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-config-25').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-graphite-25').
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-url-binders-25').
        build(this as DslFactory)

