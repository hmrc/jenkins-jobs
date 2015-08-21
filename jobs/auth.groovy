import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.jdk7EnvironmentVariable


new SbtLibraryJobBuilder('secure', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('crypto', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('ReactiveMongo', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('simple-reactivemongo', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('reactivemongo-json', jdk7EnvironmentVariable()).
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('reactivemongo-test', jdk7EnvironmentVariable()).
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-health', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-json-logger', jdk7EnvironmentVariable()).
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('Play-Reactivemongo', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('mongo-caching', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-breadcrumb', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-config', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-filters', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('domain', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('emailaddress', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('hmrctest', jdk7EnvironmentVariable()).
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('http-exceptions', jdk7EnvironmentVariable()).
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-graphite', jdk7EnvironmentVariable()).
        withoutJUnitReports().
        build(this as DslFactory)
