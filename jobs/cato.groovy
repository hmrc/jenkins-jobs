import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.jdk7EnvironmentVariable

new SbtLibraryJobBuilder('play-events', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('attachments-client', jdk7EnvironmentVariable()).
        build(this as DslFactory)
