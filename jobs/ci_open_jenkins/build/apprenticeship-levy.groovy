package ci_open_jenkins.build
import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder


def appLevyJob = new SbtMicroserviceJobBuilder('apprenticeship-levy').
        withTests('test it:test -Dplay.crypto.secret="vby[TM:LgD^9LcThW6wo_rqT[8L1Ubw<C?@4_ilVaL<pIVtO9pCmoxDv=[N^lH>9"').
        withSCoverage().
        withScalaStyle().
        build(this as DslFactory)
appLevyJob

new BuildMonitorViewBuilder('APPRENTICESHIP-LEVY-MONITOR')
        .withJobs(
        'apprenticeship-levy').build(this)