import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.GradleLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

import static java.util.Arrays.asList
import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.ChoiceParameter.choiceParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.StringParameter.stringParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.BuildDescriptionPublisher.buildDescriptionByRegexPublisher
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.createARelease

new SbtLibraryJobBuilder('sbt-git-versioning').
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('time').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-bobby').
                         build(this as DslFactory)

new GradleLibraryJobBuilder('jenkins-job-builders').
                            build(this as DslFactory)

new SbtLibraryJobBuilder('a-b-test').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('car-tax-calculator').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('domain').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('emailaddress').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('git-stamp').
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('hmrctest').
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('http-exceptions').
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('http-verbs').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('mongo-caching').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-breadcrumb').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-config').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-filters').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-graphite').
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('releaser').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-health').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-json-logger').
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-partials').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('Play-Reactivemongo').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-ui').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('reactivemongo-json').
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-bintray-publish').
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-auto-build').
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-git-stamp').
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-utils').
                         withoutJUnitReports().
                         build(this as DslFactory)
                         
new SbtLibraryJobBuilder('ReactiveMongo').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('simple-reactivemongo').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('tabular-data-validator').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('url-builder').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('worldpay-report-generator').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('reference-checker').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('accessibility-driver').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-authorisation').
        build(this as DslFactory)

new SbtLibraryJobBuilder('order-id-encoder').
        build(this as DslFactory)

new SbtLibraryJobBuilder('mongo-lock').
        build(this as DslFactory)
        
new SbtLibraryJobBuilder('play-scheduling').
        build(this as DslFactory)
        
new SbtLibraryJobBuilder('microservice-bootstrap').
        build(this as DslFactory)

jobBuilder('create-a-release').
           withParameters(stringParameter('ARTEFACT_NAME','','The artifact name e.g. cato-frontend'),
                          stringParameter('RELEASE_CANDIDATE_VERSION', '', 'The release candidate e.g. 1.3.0-1-g21312cc'),
                          choiceParameter('RELEASE_TYPE', asList('MINOR', 'MAJOR', 'PATCH'), 'The type of release e.g. MINOR')).
           withSteps(createARelease()).
           withPublishers(buildDescriptionByRegexPublisher('\\[INFO\\] Releaser successfully released (.*)')).
           build(this as DslFactory)
