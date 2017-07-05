package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtFrontendJobBuilder('help-frontend').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('frontend-bootstrap').
        build(this as DslFactory)

new SbtLibraryJobBuilder('microservice-bootstrap').
        build(this)

new SbtLibraryJobBuilder('play-ui').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-ui-23', 'play-ui', 'play-23').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-partials').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-url-binders').
        build(this as DslFactory)

new SbtLibraryJobBuilder('logback-json-logger').
        withoutJUnitReports().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('epaye-api').
        withTests("test").
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('epaye-api-stub').
        withTests("test").
        withScalaStyle().
        build(this as DslFactory)

new BuildMonitorViewBuilder('BTA-OPEN-DEV-MONITOR')
        .withJobs('help-frontend',
                  'frontend-bootstrap',
                  'microservice-bootstrap',
                  'play-graphite',
                  'play-json-encoder',
                  'play-ui',
                  'play-filters',
                  'play-partials',
                  'play-config',
                  'play-url-binders',
                  'logback-json-logger',
                  'epaye-api').build(this)
