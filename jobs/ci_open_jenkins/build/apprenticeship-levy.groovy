package ci_open_jenkins.build
import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder



new SbtFrontendJobBuilder('apprenticeship-levy').
        build(this as DslFactory)