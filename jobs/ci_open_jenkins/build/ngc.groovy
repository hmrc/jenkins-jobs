package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('customer-profile').build(this as DslFactory)

new SbtFrontendJobBuilder('personal-income').build(this as DslFactory)

new SbtFrontendJobBuilder('mobile-messages').build(this as DslFactory)

new SbtLibraryJobBuilder('play-hmrc-api').build(this)

new BuildMonitorViewBuilder('NGC-MONITOR')
        .withJobs('personal-income', 
        	'customer-profile',
        	'mobile-messages',
        	'play-hmrc-api').build(this)

