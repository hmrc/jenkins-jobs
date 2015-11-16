import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtLibraryJobBuilder('http-verbs').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-auditing').
        build(this as DslFactory)

new SbtLibraryJobBuilder('http-verbs-test').
		withoutJUnitReports().
        build(this as DslFactory)
