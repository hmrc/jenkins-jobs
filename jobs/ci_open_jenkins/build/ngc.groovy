package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('customer-profile').build(this as DslFactory)

new SbtFrontendJobBuilder('personal-income').build(this as DslFactory)

new SbtFrontendJobBuilder('mobile-messages').build(this as DslFactory)

new SbtFrontendJobBuilder('submission-tracker').build(this as DslFactory)

new SbtFrontendJobBuilder('push-notification').build(this as DslFactory)

new SbtFrontendJobBuilder('push-registration').build(this as DslFactory)

new SbtFrontendJobBuilder('native-apps-api-orchestration').build(this as DslFactory)

new SbtMicroserviceJobBuilder('api-gateway-async-example').build(this as DslFactory)

new SbtFrontendJobBuilder('mobile-token-proxy').build(this as DslFactory)

new SbtMicroserviceJobBuilder('mobile-token-exchange').build(this as DslFactory)

new SbtLibraryJobBuilder('play-hmrc-api').build(this)

new SbtLibraryJobBuilder('play-hal').build(this)

new SbtLibraryJobBuilder('play-async').build(this as DslFactory)

new SbtLibraryJobBuilder('microservice-async').build(this as DslFactory)

new BuildMonitorViewBuilder('NGC-MONITOR')
		.withJobs('personal-income',
		'customer-profile',
		'mobile-messages',
		'push-notification',
		'push-registration',
		'submission-tracker',
		'native-apps-api-orchestration',
		'api-gateway-async-example',
		'play-hmrc-api',
		'play-async',
		'microservice-async',
		'play-hal',
		'mobile-token-exchange',
		'mobile-token-proxy').build(this)


