import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder

new SbtFrontendJobBuilder('help-frontend').
                         build(this as DslFactory)
