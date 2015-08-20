import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable
import uk.gov.hmrc.jenkinsjobs.domain.builder.GradleLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

import static java.util.Arrays.asList
import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.ChoiceParameter.choiceParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.StringParameter.stringParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.BuildDescriptionPublisher.buildDescriptionByRegexPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.jdk7EnvironmentVariable
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.StringEnvironmentVariable.stringEnvironmentVariable
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.createARelease

new SbtLibraryJobBuilder('sbt-git-versioning', jdk7EnvironmentVariable()).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('time', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-bobby', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new GradleLibraryJobBuilder('jenkins-job-builders').
                            build(this as DslFactory)

new SbtLibraryJobBuilder('a-b-test', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('car-tax-calculator', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('domain', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('emailaddress', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('git-stamp', jdk7EnvironmentVariable()).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('hmrctest', jdk7EnvironmentVariable()).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('http-exceptions', jdk7EnvironmentVariable()).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('http-verbs', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('mongo-caching', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-breadcrumb', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-config', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-filters', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-graphite', jdk7EnvironmentVariable()).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('releaser', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-health', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-json-logger', jdk7EnvironmentVariable()).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-partials', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('Play-Reactivemongo', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-ui', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('govuk-template', jdk7EnvironmentVariable()).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('frontend-bootstrap', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('reactivemongo-json', jdk7EnvironmentVariable()).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('reactivemongo-test', jdk7EnvironmentVariable()).
                         withoutJUnitReports().
                         build(this as DslFactory)                         

new SbtLibraryJobBuilder('sbt-bintray-publish', jdk7EnvironmentVariable()).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-auto-build', jdk7EnvironmentVariable()).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-git-stamp', jdk7EnvironmentVariable()).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-utils', jdk7EnvironmentVariable()).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-distributables', jdk7EnvironmentVariable()).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('secure', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('crypto', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('ReactiveMongo', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('simple-reactivemongo', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('tabular-data-validator', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('url-builder', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('worldpay-report-generator', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('reference-checker', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('accessibility-driver', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-events', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('attachments-client', jdk7EnvironmentVariable()).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-authorisation', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('order-id-encoder', jdk7EnvironmentVariable()).
        build(this as DslFactory)

new SbtLibraryJobBuilder('mongo-lock', jdk7EnvironmentVariable()).
        build(this as DslFactory)
        
new SbtLibraryJobBuilder('play-scheduling', jdk7EnvironmentVariable()).
        build(this as DslFactory)
        
new SbtLibraryJobBuilder('microservice-bootstrap', jdk7EnvironmentVariable()).
        build(this as DslFactory)
        
new SbtLibraryJobBuilder('batch-updater', jdk7EnvironmentVariable()).
        build(this as DslFactory)

jobBuilder('create-a-release', jdk7EnvironmentVariable()).
           withEnvironmentVariables(stringEnvironmentVariable('RELEASER_VERSION', '0.4.0')).
           withParameters(stringParameter('ARTEFACT_NAME','','The artifact name e.g. cato-frontend'),
                          stringParameter('RELEASE_CANDIDATE_VERSION', '', 'The release candidate e.g. 1.3.0-1-g21312cc'),
                          choiceParameter('RELEASE_TYPE', asList('MINOR', 'MAJOR', 'PATCH'), 'The type of release e.g. MINOR')).
           withSteps(createARelease()).
           withPublishers(buildDescriptionByRegexPublisher('\\[INFO\\] Releaser successfully released (.*)')).
           build(this as DslFactory)
