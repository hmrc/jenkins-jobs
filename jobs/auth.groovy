import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.JDK7

new SbtLibraryJobBuilder('http-caching-client').
        build(this as DslFactory)

new SbtLibraryJobBuilder('json-encryption').
        build(this as DslFactory)

new SbtLibraryJobBuilder('secure').
        build(this as DslFactory)

new SbtLibraryJobBuilder('crypto').
        build(this as DslFactory)

new SbtLibraryJobBuilder('simple-reactivemongo').
        build(this as DslFactory)

new SbtLibraryJobBuilder('reactivemongo-json').
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('reactivemongo-test').
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-health').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-json-logger').
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('Play-Reactivemongo').
        build(this as DslFactory)

new SbtLibraryJobBuilder('mongo-caching').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-breadcrumb').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-config').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-filters').
        build(this as DslFactory)

new SbtLibraryJobBuilder('domain').
        build(this as DslFactory)

new SbtLibraryJobBuilder('hmrctest').
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('http-exceptions').
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-graphite').
        withoutJUnitReports().
        build(this as DslFactory)
