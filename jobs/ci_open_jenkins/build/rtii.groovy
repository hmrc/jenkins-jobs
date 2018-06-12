package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('real-time-income-information').
        withTests("test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('retrieve-citizen-income-stub').
        withTests("test").
        build(this as DslFactory)


