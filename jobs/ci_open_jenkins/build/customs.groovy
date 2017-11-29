package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder

def customsInventoryLinkingImports = 'customs-inventory-linking-imports'

new SbtMicroserviceJobBuilder(customsInventoryLinkingImports).
    build(this as DslFactory)

new BuildMonitorViewBuilder('CUSTOMS-MONITOR').
    withJobs(customsInventoryLinkingImports).
    build(this)
