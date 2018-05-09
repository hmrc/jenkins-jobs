package ci_open_jenkins.build
worldpay-downloader
import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('worldpay-downloader').
						 withTests("test it:test fun:test").	
						 withHtmlReports('target/fun-test-reports/cucumber': 'Functional tests HTML Report').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('worldpay-report-generator').
        build(this as DslFactory)

new SbtLibraryJobBuilder('reference-checker').
        build(this as DslFactory)

new SbtLibraryJobBuilder('mongo-lock').
        build(this as DslFactory)

new SbtLibraryJobBuilder('order-id-encoder').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('change-bank-account-frontend').
		withDeployToDevelopment().
		withTests("test it:test").
		build(this)

new SbtMicroserviceJobBuilder('change-bank-account').
		withDeployToDevelopment().
		withTests("test it:test").
		build(this)

new SbtMicroserviceJobBuilder('change-bank-account-stubs').
		withDeployToDevelopment().
		withTests("test it:test").
		build(this)

new BuildMonitorViewBuilder('PAYMENTS-MONITOR')
        .withJobs('worldpay-downloader',
                  'worldpay-report-generator',
                  'reference-checker',
                  'order-id-encoder',
                  'mongo-lock',
		          'change-bank-account-frontend',
		          'change-bank-account',
		           'change-bank-account-stubs'
                ).build(this)
