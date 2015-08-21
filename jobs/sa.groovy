import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.JDK7

new SbtLibraryJobBuilder('a-b-test', JDK7).
        build(this as DslFactory)

new SbtLibraryJobBuilder('url-builder', JDK7).
        build(this as DslFactory)
