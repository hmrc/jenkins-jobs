package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new SbtFrontendJobBuilder('epaye-frontend')
        .withTests("test")
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('epaye')
        .withTests("test")
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('epaye-api')
        .withTests("test")
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new BuildMonitorViewBuilder('EPAYE-MONITOR')
        .withJobs(
        'epaye-frontend',
        'epaye',
        'epaye-api')
        .build(this)