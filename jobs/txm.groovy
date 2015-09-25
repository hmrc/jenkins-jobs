import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.JDK7

new SbtLibraryJobBuilder('http-verbs').
        build(this as DslFactory)

new SbtLibraryJobBuilder('http-verbs-test').
		withoutJUnitReports().
        build(this as DslFactory)
