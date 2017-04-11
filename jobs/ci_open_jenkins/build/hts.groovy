package ci_open_jenkins.build
import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder


new SbtFrontendJobBuilder('help-to-save-frontend').
        build(this as DslFactory)

new SbtFrontendJobBuilder('help-to-save-eligibility-check').
        build(this as DslFactory)