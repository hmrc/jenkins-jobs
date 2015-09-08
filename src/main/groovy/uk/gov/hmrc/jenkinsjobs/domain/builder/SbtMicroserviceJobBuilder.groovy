package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.JobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable

import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.BuildDescriptionPublisher.buildDescriptionByRegexPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable.JDK8
import static uk.gov.hmrc.jenkinsjobs.domain.builder.JobBuilders.jobBuilder
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.defaultHtmlReportsPublisher
import static uk.gov.hmrc.jenkinsjobs.domain.publisher.Publishers.defaultJUnitReportsPublisher
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.sbtCleanDistPublish
import static uk.gov.hmrc.jenkinsjobs.domain.step.Steps.sbtCleanDistTgzPublish

final class SbtMicroserviceJobBuilder implements Builder<Job> {

    private JobBuilder jobBuilder
    private String sbtTests = "test it:test"
    private JdkEnvironmentVariable jdk

    SbtMicroserviceJobBuilder(String name, JdkEnvironmentVariable jdk = JDK8) {
        jobBuilder = jobBuilder(name, name, jdk).                                
                                withPublishers(defaultHtmlReportsPublisher(),
                                               buildDescriptionByRegexPublisher('.*sbt git versioned as ([\\w\\d\\.\\-]+)'),
                                               defaultJUnitReportsPublisher())
        this.jdk = jdk;                        
    }

    Job build(DslFactory dslFactory) {
        jobBuilder.
        withSteps(jdk.isJdk8() ? sbtCleanDistTgzPublish(sbtTests) : sbtCleanDistPublish(sbtTests)).
        build(dslFactory)
    }

    SbtMicroserviceJobBuilder withTests(String tests) {
        this.sbtTests = tests
        this
    }
}