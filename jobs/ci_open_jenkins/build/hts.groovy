package ci_open_jenkins.build
import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder


new SbtFrontendJobBuilder('help-to-save-frontend')
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('help-to-save-eligibility-check')
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('help-to-save-stub')
        .build(this as DslFactory)

