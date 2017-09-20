package ci_open_jenkins.build

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtMicroserviceJobBuilder('capital-gains-calculator')
        .withScalaStyle()
        .withSCoverage()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('cgt-calculator-non-resident-frontend')
        .withScalaStyle()
        .withSCoverage()
        .withExtendedTimeout()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('cgt-calculator-resident-shares-frontend')
        .withScalaStyle()
        .withSCoverage()
        .withExtendedTimeout()
        .build(this as DslFactory)

new SbtMicroserviceJobBuilder('cgt-calculator-resident-properties-frontend')
        .withScalaStyle()
        .withSCoverage()
        .withExtendedTimeout()
        .build(this as DslFactory)

new BuildMonitorViewBuilder('DDCT-LIVE-SERVICES-MONITOR')
        .withJobs(
        'capital-gains-calculator',
        'cgt-calculator-non-resident-frontend',
        'cgt-calculator-resident-shares-frontend',
        'cgt-calculator-resident-properties-frontend')
        .build(this)
