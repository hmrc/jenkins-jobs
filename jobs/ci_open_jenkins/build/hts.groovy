package ci_open_jenkins.build
import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder


new SbtFrontendJobBuilder('help-to-save-frontend')
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('help-to-save-eligibility-check')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('help-to-save-stub')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

