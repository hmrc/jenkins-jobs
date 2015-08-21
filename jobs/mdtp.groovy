import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.GradleLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

import static java.util.Arrays.asList
import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.ChoiceParameter.choiceParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.StringParameter.stringParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.BuildDescriptionPublisher.buildDescriptionByRegexPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.JDK7
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.StringEnvironmentVariable.stringEnvironmentVariable
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.createARelease

new SbtLibraryJobBuilder('sbt-git-versioning', JDK7).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('time', JDK7).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-bobby', JDK7).
                         build(this as DslFactory)

new GradleLibraryJobBuilder('jenkins-job-builders').
                            build(this as DslFactory)

new SbtLibraryJobBuilder('git-stamp', JDK7).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('releaser', JDK7).
                         build(this as DslFactory)

new SbtLibraryJobBuilder('govuk-template', JDK7).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-bintray-publish', JDK7).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-auto-build', JDK7).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-git-stamp', JDK7).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-utils', JDK7).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-distributables', JDK7).
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('play-authorisation', JDK7).
        build(this as DslFactory)

new SbtLibraryJobBuilder('order-id-encoder', JDK7).
        build(this as DslFactory)

        
new SbtLibraryJobBuilder('play-scheduling', JDK7).
        build(this as DslFactory)
        
new SbtLibraryJobBuilder('microservice-bootstrap', JDK7).
        build(this as DslFactory)
        
new SbtLibraryJobBuilder('batch-updater', JDK7).
        build(this as DslFactory)

jobBuilder('create-a-release', JDK7).
           withEnvironmentVariables(stringEnvironmentVariable('RELEASER_VERSION', '0.4.0')).
           withParameters(stringParameter('ARTEFACT_NAME','','The artifact name e.g. cato-frontend'),
                          stringParameter('RELEASE_CANDIDATE_VERSION', '', 'The release candidate e.g. 1.3.0-1-g21312cc'),
                          choiceParameter('RELEASE_TYPE', asList('MINOR', 'MAJOR', 'PATCH'), 'The type of release e.g. MINOR')).
           withSteps(createARelease()).
           withPublishers(buildDescriptionByRegexPublisher('\\[INFO\\] Releaser successfully released (.*)')).
           build(this as DslFactory)