import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.ListViewBuilder
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

jobBuilder('create-a-release').
           withParameters(stringParameter('ARTEFACT_NAME','','The artifact name e.g. cato-frontend'),
                          stringParameter('RELEASE_CANDIDATE_VERSION', 'The release candidate e.g. 1.3.0-1-g21312cc'),
                          choiceParameter('RELEASE_TYPE', asList('MINOR', 'MAJOR', 'PATCH'), 'The type of release e.g. MINOR'),
                          stringParameter('RELEASER_VERSION', '0.4.0', 'The version of Releaser e.g. 0.1.0')).
           withSteps(createARelease()).
           withPublishers(buildDescriptionByRegexPublisher('\\[INFO\\] Releaser successfully released (.*)')).
           build(this as DslFactory)
                         
new ListViewBuilder('mdtp').
                    withJobsRegex('releaser.*').build(this as DslFactory)
