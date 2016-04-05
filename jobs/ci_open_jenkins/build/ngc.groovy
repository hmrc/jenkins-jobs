package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('next-generation-consumer').build(this as DslFactory)

new SbtMicroserviceJobBuilder('customer-profile').build(this as DslFactory)

new SbtFrontendJobBuilder('personal-income').build(this as DslFactory)

new BuildMonitorViewBuilder('NGC-MONITOR')
        .withJobs('next-generation-consumer','personal-income').build(this)

