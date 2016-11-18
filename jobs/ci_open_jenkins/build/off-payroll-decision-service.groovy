package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('off-payroll-decision-service').
        build(this as DslFactory)



