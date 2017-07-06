package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

final CronExpressionForOnceEveryDay = "H(0-59) H(3-5) * * *"

new SbtLibraryJobBuilder('http-caching-client').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('json-encryption').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('secure').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('crypto').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('passcode-verification').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('simple-reactivemongo').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('reactivemongo-json').
        withCronTrigger(CronExpressionForOnceEveryDay).
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('reactivemongo-test').
        withCronTrigger(CronExpressionForOnceEveryDay).
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-health').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-json-logger').
        withCronTrigger(CronExpressionForOnceEveryDay).
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('Play-Reactivemongo').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('mongo-caching').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-breadcrumb').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-config').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-filters').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('domain').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('hmrctest').
        withCronTrigger(CronExpressionForOnceEveryDay).
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('http-exceptions').
        withCronTrigger(CronExpressionForOnceEveryDay).
        withoutJUnitReports().
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('openid-connect-userinfo')
        .withTests("test it:test")
        .build(this as DslFactory)

new SbtLibraryJobBuilder('play-graphite').
        withCronTrigger(CronExpressionForOnceEveryDay).
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-authorised-frontend').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-authorisation').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-auth').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('dynamic-stub').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new SbtLibraryJobBuilder('tax-year').
        withCronTrigger(CronExpressionForOnceEveryDay).
        build(this as DslFactory)

new BuildMonitorViewBuilder('AUTH-MONITOR')
        .withJobs(
            'crypto',
            'domain',
            'hmrctest',
            'http-caching-client',
            'http-exceptions',
            'json-encryption',
            'mongo-caching',
            'openid-connect-userinfo',
            'play-auth',
            'play-authorisation',
            'play-authorised-frontend',
            'secure',
            'tax-year'
        )
        .build(this)
