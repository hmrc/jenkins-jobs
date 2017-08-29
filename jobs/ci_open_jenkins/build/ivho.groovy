package ci_open_jenkins.build
import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('individuals-api').
        withTests("test it:test component:test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('individuals-employments-api').
        withTests("test it:test component:test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('individuals-income-api').
        withTests("test it:test component:test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('individual-details-des-stub').
        withTests("test it:test component:test").
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('individual-income-des-stub').
        withTests("test it:test component:test").
        build(this as DslFactory)

new BuildMonitorViewBuilder('IVHO-MONITOR')
        .withJobs(
        'individuals-api',
        'individuals-employments-api',
        'individuals-income-api',
        'individual-details-des-stub',
        'individual-income-des-stub'
).build(this)
