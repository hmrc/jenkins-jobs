import uk.gov.hmrc.jenkinsjobs.domain.builder.GradleLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

import static java.util.Arrays.asList
import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.ChoiceParameter.choiceParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.StringParameter.stringParameter
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.BuildDescriptionPublisher.buildDescriptionByRegexPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.JDK7
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.StringEnvironmentVariable.stringEnvironmentVariable
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.scm.HmrcGitHubComScm.hmrcGitHubComScm
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.createARelease
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.initARepository
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.initARepositoryPy

new SbtLibraryJobBuilder('sbt-git-versioning', JDK7).
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('time').
                         build(this)

new SbtLibraryJobBuilder('sbt-bobby', JDK7).
                         build(this)

new GradleLibraryJobBuilder('jenkins-job-builders').
                            build(this)

new SbtLibraryJobBuilder('git-stamp').
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('init-repository').
                        build(this)

new SbtLibraryJobBuilder('releaser', JDK7).
                         build(this)

new SbtLibraryJobBuilder('govuk-template').
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('sbt-bintray-publish', JDK7).
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('sbt-auto-build').
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('sbt-git-stamp', JDK7).
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('sbt-settings').
                         withoutJUnitReports().
                         build(this)

new SbtLibraryJobBuilder('sbt-distributables', JDK7).
                         withoutJUnitReports().
                         build(this)
        
new SbtLibraryJobBuilder('play-scheduling').
        build(this)
                
new SbtLibraryJobBuilder('batch-updater', JDK7).
        build(this)

new SbtLibraryJobBuilder('bulk-entity-streaming').
        build(this)

jobBuilder("init-repository-py").
           withParameters(stringParameter('REPOSITORY_NAME', '', 'The GitHub repository name')).
           withScm(hmrcGitHubComScm('init-repository-py')).
           withSteps(initARepositoryPy('$REPOSITORY_NAME')).
           withPublishers(buildDescriptionByRegexPublisher('Created (.*) in releases')).
           build(this)
           

jobBuilder('create-a-repository').
          withEnvironmentVariables(stringEnvironmentVariable('INIT_REPO_VERSION', '0.7.0')).
          withParameters(stringParameter('REPOSITORY_NAME','','The repository name e.g. foo-frontend')).
          withParameters(stringParameter('TEAM_NAME','','The exact name of the github team to which the repository will be added')).          
          withSteps(initARepository('$REPOSITORY_NAME', '$TEAM_NAME')).
          withPublishers(buildDescriptionByRegexPublisher('\\[INFO\\] Github repositories and Bintray packages successfully created (.*)')).
          build(this)

jobBuilder('create-a-release').
           withEnvironmentVariables(stringEnvironmentVariable('RELEASER_VERSION', '0.9.0')).
           withParameters(stringParameter('ARTEFACT_NAME','','The artifact name e.g. cato-frontend'),
                          stringParameter('RELEASE_CANDIDATE_VERSION', '', 'The release candidate e.g. 1.3.0-1-g21312cc'),
                          choiceParameter('RELEASE_TYPE', asList('MINOR', 'MAJOR', 'PATCH'), 'The type of release e.g. MINOR')).
           withSteps(createARelease()).
           withPublishers(buildDescriptionByRegexPublisher('\\[INFO\\] Releaser successfully released (.*)')).
           build(this)