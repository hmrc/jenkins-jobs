import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.JDK7

new SbtLibraryJobBuilder('play-events', JDK7).
        build(this as DslFactory)

new SbtLibraryJobBuilder('attachments-client', JDK7).
        build(this as DslFactory)
