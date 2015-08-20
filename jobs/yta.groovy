import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.jdk7EnvironmentVariable

new SbtFrontendJobBuilder('help-frontend', jdk7EnvironmentVariable()).
                         build(this as DslFactory)
