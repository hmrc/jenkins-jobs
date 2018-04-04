package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtFrontendJobBuilder('childcare-calculator-frontend').
        withScalaStyle().
        withSCoverage().
        withTests("test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('cc-calculator').
        withScalaStyle().
        withTests("test").
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('cc-eligibility').
        withScalaStyle().
        withSCoverage().
        withTests("test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('cc-email-capture').
        withScalaStyle().
        withSCoverage().
        withTests("test").
        build(this as DslFactory)

new BuildMonitorViewBuilder('CC-MONITOR').
        withJobs('childcare-calculator-frontend', 'cc-calculator', 'cc-eligibility', 'cc-email-capture').
        build(this)
