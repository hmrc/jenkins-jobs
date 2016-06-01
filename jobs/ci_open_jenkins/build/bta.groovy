package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('bta-persistence').
		build(this)

new SbtFrontendJobBuilder('help-frontend').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('frontend-bootstrap').
        build(this as DslFactory)

new SbtLibraryJobBuilder('microservice-bootstrap').
        build(this)

new SbtLibraryJobBuilder('play-ui').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-partials').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-url-binders').
        build(this as DslFactory)


new BuildMonitorViewBuilder('BTA-OPEN-DEV-MONITOR')
        .withJobs('bta-persistence',
                  'help-frontend',
                  'frontend-bootstrap',
                  'microservice-bootstrap',
                  'play-graphite',
                  'play-json-encoder',
                  'play-ui',
                  'play-filters',
                  'play-partials',
                  'play-config',
                  'play-url-binders').build(this)


