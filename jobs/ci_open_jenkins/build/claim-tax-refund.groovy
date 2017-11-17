package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder

new BuildMonitorViewBuilder('CTR-Monitor')
        .withJobs(
        'claim-tax-refund-frontend',
        'claim-tax-refund-acceptance-tests')
        .build(this as DslFactory)

new SbtFrontendJobBuilder('claim-tax-refund-frontend', 'hmrc/claim-tax-refund-frontend').
        withXvfb().
        withDeployToDev().
        withStaticAnalysisAndReport().
        withDownstreamJobs('claim-tax-refund-acceptance-tests').
        build(this as DslFactory)
        
