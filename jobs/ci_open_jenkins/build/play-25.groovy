package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

//play 2.5 jobs
new SbtLibraryJobBuilder('sbt-distributables-25').
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('sbt-distributables-plugin-25').
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('sbt-auto-build-25').
                         withoutJUnitReports().
                        build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-git-versioning-25').
                        withoutJUnitReports().
                        build(this as DslFactory)

new SbtLibraryJobBuilder('http-verbs-25').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-auditing-25').
        build(this as DslFactory)

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

new SbtLibraryJobBuilder('frontend-bootstrap-25').
        build(this as DslFactory)

new SbtLibraryJobBuilder('hmrctest-25').
        build(this as DslFactory)

new SbtLibraryJobBuilder('microservice-bootstrap-25').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-filters-25').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-authorisation-25').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-partials-25').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-authorised-frontend-25').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-ui-25').
        build(this as DslFactory)

