package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.Job
import spock.lang.Specification
import uk.gov.hmrc.jenkinsjobs.JobParents

@Mixin(JobParents)
class GradleLibraryReleaseJobBuilderSpec extends Specification {

    void 'test XML output'() {
        given:
        GradleLibraryReleaseJobBuilder jobBuilder = new GradleLibraryReleaseJobBuilder('test-job')

        when:
        Job job = jobBuilder.build(jobParent())

        then:
        with(job.node) {
            scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.url.text() == 'git@github.com:hmrc/test-job.git'
            scm.branches.'hudson.plugins.git.BranchSpec'.name.text() == '${TAG}'

            def parameterDefinitions = it.properties.'hudson.model.ParametersDefinitionProperty'.'parameterDefinitions'
            1 == parameterDefinitions.size()
            def tagParameter = parameterDefinitions[0].'hudson.model.StringParameterDefinition'
            tagParameter.name.text() == "TAG"
            !tagParameter.defaultValue.text()
            tagParameter.description.text() == "The tag to build and release e.g. release/11.0.0"

            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('CLASSPATH')
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('JAVA_HOME')
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('PATH')
            buildWrappers.'EnvInjectBuildWrapper'.info.propertiesContent.text().contains('TMP')
            triggers.'com.cloudbees.jenkins.gitHubPushTrigger'.spec.text() == ''
            builders.'hudson.plugins.gradle.Gradle'.tasks.text().contains('clean test bintrayUpload --info')
            buildWrappers.'hudson.plugins.build__timeout.BuildTimeoutWrapper'.strategy.timeoutMinutes.text() == '10'
        }
    }

    void 'test extended build timeout'() {
        given:
        GradleLibraryReleaseJobBuilder jobBuilder = new GradleLibraryReleaseJobBuilder('test-job')

        when:
        Job job = jobBuilder.withExtendedTimeout().build(jobParent())

        then:
        with(job.node) {
            buildWrappers.'hudson.plugins.build__timeout.BuildTimeoutWrapper'.strategy.timeoutMinutes.text() == '20'
        }
    }
}
