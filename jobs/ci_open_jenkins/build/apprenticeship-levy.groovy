package ci_open_jenkins.build
import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder


new SbtMicroserviceJobBuilder('apprenticeship-levy').
        build(this as DslFactory)
	
new SbtMicroserviceJobBuilder('apprenticeship-levy-stub').
        build(this as DslFactory)	