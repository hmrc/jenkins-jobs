package ci_open_jenkins.build
import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder


new SbtFrontendJobBuilder('help-to-save-frontend').
        build(this as DslFactory)