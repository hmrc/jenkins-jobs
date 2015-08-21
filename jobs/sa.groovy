import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.jdk7EnvironmentVariable

new SbtLibraryJobBuilder('a-b-test', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('url-builder', jdk7EnvironmentVariable()).
        build(this as DslFactory)
