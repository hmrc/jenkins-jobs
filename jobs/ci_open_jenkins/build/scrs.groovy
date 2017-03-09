package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtFrontendJobBuilder('business-registration-frontend').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('incorporation-dynamic-stub').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('business-registration').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('company-registration').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)

<<<<<<< HEAD
new SbtFrontendJobBuilder('company-registration-scrs-5444-hotfix', 'company-registration', 'SCRS-5444').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('business-registration-notification').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)   

new SbtMicroserviceJobBuilder('business-registration-dynamic-stub').
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

new BuildMonitorViewBuilder('SCRS-MONITOR')
        .withJobs('business-registration-frontend', 'incorporation-dynamic-stub', 'business-registration', 'company-registration', 'business-registration-notification', 'business-registration-dynamic-stub', 'vat-registration-frontend', 'vat-registration').build(this)
=======
new SbtMicroserviceJobBuilder('incorporation-information').
        withScalaStyle().
        withSCoverage().
        build(this as DslFactory)        

new BuildMonitorViewBuilder('SCRS-MONITOR')
.withJobs('business-registration-frontend', 'incorporation-dynamic-stub', 'business-registration', 'company-registration', 'incorporation-information').build(this)
>>>>>>> Amended scrs.groovy for new incorporation-information mservice
