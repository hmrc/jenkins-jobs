import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtLibraryJobBuilder('http-caching-client').
        build(this as DslFactory)

new SbtLibraryJobBuilder('json-encryption').
        build(this as DslFactory)

new SbtLibraryJobBuilder('secure').
        build(this as DslFactory)

new SbtLibraryJobBuilder('crypto').
        build(this as DslFactory)

new SbtLibraryJobBuilder('simple-reactivemongo').
        build(this as DslFactory)

new SbtLibraryJobBuilder('reactivemongo-json').
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('reactivemongo-test').
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-health').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-json-logger').
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('Play-Reactivemongo').
        build(this as DslFactory)

new SbtLibraryJobBuilder('mongo-caching').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-breadcrumb').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-config').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-filters').
        build(this as DslFactory)

new SbtLibraryJobBuilder('domain').
        build(this as DslFactory)

new SbtLibraryJobBuilder('hmrctest').
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('http-exceptions').
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-graphite').
        withoutJUnitReports().
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-authorised-frontend').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-authorisation').
        build(this as DslFactory)

new SbtLibraryJobBuilder('play-authorisation-httpverbs-hotfix', 'play-authorisation', 'hotfix/httpverbs').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('phone-number').
	withTests("test it:test").	
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('sms-gateway').
        withTests("test it:test").      
        build(this as DslFactory)        

new BuildMonitorViewBuilder('GG-IN-THE-OPEN-MONITOR')
        .withJobs('phone-number','sms-gateway').build(this)