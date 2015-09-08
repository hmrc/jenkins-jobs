package uk.gov.hmrc.jenkinsjobs.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.JobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.variables.JdkEnvironmentVariable
import uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.Publisher

import static java.util.Arrays.asList
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
    private boolean withJUnitReports = true
    private List<Publisher> additionalPublishers = new ArrayList<>()


    SbtMicroserviceJobBuilder(String name, JdkEnvironmentVariable jdk = JDK8) {
        jobBuilder = jobBuilder(name, name, jdk)
        this.jdk = jdk;                        
    }

    Job build(DslFactory dslFactory) {
        jobBuilder.
        withSteps(jdk.isJdk8() ? sbtCleanDistTgzPublish(sbtTests) : sbtCleanDistPublish(sbtTests)).
        withPublishers(publishers()).
        build(dslFactory)
    }

    SbtMicroserviceJobBuilder withTests(String tests) {
        this.sbtTests = tests
        this
    }

    SbtMicroserviceJobBuilder withoutJUnitReports() {
        this.withJUnitReports = false
        this
    }

    SbtMicroserviceJobBuilder withAdditionalPublisher(Publisher publisher) {
        this.additionalPublishers.add(publisher)
        this
    }    

    private ArrayList<Publisher> publishers() {
        List<Publisher> publishers = new ArrayList<>(asList(defaultHtmlReportsPublisher(), buildDescriptionByRegexPublisher('.*sbt git versioned as ([\\w\\d\\.\\-]+)')))
        if (withJUnitReports) {
            publishers.add(defaultJUnitReportsPublisher())
        }
        publishers.addAll(additionalPublishers)
        publishers
    }
}