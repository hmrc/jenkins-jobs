package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtScalaJsLibraryJobBuilder

new SbtLibraryJobBuilder('play-hmrc-api').build(this)

new SbtLibraryJobBuilder('play-hal').build(this)

new SbtLibraryJobBuilder('play-async').build(this as DslFactory)

new SbtLibraryJobBuilder('microservice-async').build(this as DslFactory)

new SbtScalaJsLibraryJobBuilder('paye-estimator').withoutJUnitReports().build(this)

new SbtMicroserviceJobBuilder('authenticate-one-time-password').build(this as DslFactory)

new BuildMonitorViewBuilder('NGC-MONITOR')
        .withJobs('play-hmrc-api',
        'play-async',
        'microservice-async',
        'play-hal',
        'paye-estimator',
        'authenticate-one-time-password')
        .build(this)
