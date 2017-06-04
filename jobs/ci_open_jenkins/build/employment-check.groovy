package ci_open_jenkins.build
import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder


new SbtMicroserviceJobBuilder('employment-check').
        build(this as DslFactory)
	
new SbtMicroserviceJobBuilder('employment-check-stub').
        build(this as DslFactory)	