import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.JDK7

new SbtLibraryJobBuilder('json-encryption', JDK7).
        build(this as DslFactory)

new SbtLibraryJobBuilder('secure', JDK7).
        build(this as DslFactory)

new SbtLibraryJobBuilder('crypto', JDK7).
        build(this as DslFactory)

new SbtLibraryJobBuilder('simple-reactivemongo', JDK7).
        build(this as DslFactory)

new SbtLibraryJobBuilder('reactivemongo-json', JDK7).
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('reactivemongo-test', JDK7).
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-health').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-json-logger', JDK7).
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('Play-Reactivemongo', JDK7).
        build(this as DslFactory)

new SbtLibraryJobBuilder('mongo-caching', JDK7).
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-breadcrumb', JDK7).
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-config', JDK7).
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-filters', JDK7).
        build(this as DslFactory)

new SbtLibraryJobBuilder('domain', JDK7).
        build(this as DslFactory)

new SbtLibraryJobBuilder('emailaddress', JDK7).
        build(this as DslFactory)

new SbtLibraryJobBuilder('hmrctest', JDK7).
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('http-exceptions', JDK7).
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-graphite', JDK7).
        withoutJUnitReports().
        build(this as DslFactory)
