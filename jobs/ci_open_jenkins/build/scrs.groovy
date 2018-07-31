package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('business-registration').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('company-registration').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('company-registration-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('company-registration-eligibility-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)        

new SbtMicroserviceJobBuilder('business-registration-notification').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)   

new SbtMicroserviceJobBuilder('business-registration-dynamic-stub').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('incorporation-information').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('vat-registration-eligibility-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('vat-registration-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('vat-registration').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('paye-registration-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtFrontendJobBuilder('paye-registration-eligibility-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)      

new SbtMicroserviceJobBuilder('paye-registration').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

// new SbtMicroserviceJobBuilder('industry-classification-lookup-frontend').
//        withScalaStyle().
//        withSCoverage().
//        build(this as DslFactory)

//new SbtMicroserviceJobBuilder('industry-classification-lookup').
//        withScalaStyle().
//        withSCoverage().
//        build(this as DslFactory)

new BuildMonitorViewBuilder('SCRS-MONITOR')
        .withJobs('business-registration',
                  'company-registration',
                  'company-registration-frontend',
                  'company-registration-eligibility-frontend',
                  'business-registration-notification',
                  'business-registration-dynamic-stub',
                  'incorporation-information',
                  'vat-registration-eligibility-frontend',
                  'vat-registration-frontend',
                  'vat-registration',
                  'paye-registration-frontend',
                  'paye-registration-eligibility-frontend',
                  'paye-registration',
                  'industry-classification-lookup-frontend',
                  'industry-classification-lookup').build(this)
