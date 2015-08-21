import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.jdk7EnvironmentVariable

new SbtFrontendJobBuilder('help-frontend', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('worldpay-report-generator', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('reference-checker', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('mongo-lock', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('frontend-bootstrap', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-ui', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-partials', jdk7EnvironmentVariable()).
        build(this as DslFactory)
