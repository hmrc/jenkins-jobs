package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('retrieve-citizen-income-stub').
        withScalaStyle().
        withSCoverage().
        withTests("test").
        build(this as DslFactory)
