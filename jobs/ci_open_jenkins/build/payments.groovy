package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtLibraryJobBuilder('worldpay-report-generator').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder("cds-stub").
        build(this as DslFactory)

new SbtLibraryJobBuilder('reference-checker').
        build(this as DslFactory)

new SbtLibraryJobBuilder('order-id-encoder').
        build(this as DslFactory)

new BuildMonitorViewBuilder('PAYMENTS-MONITOR')
        .withJobs('worldpay-report-generator',
                  'reference-checker',
                  'order-id-encoder'
                ).build(this)
