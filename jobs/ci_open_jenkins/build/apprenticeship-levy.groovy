package ci_open_jenkins.build
import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtFrontendJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder


new SbtMicroserviceJobBuilder('apprenticeship-levy').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('apprenticeship-levy-stub').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('employment-check').
        build(this as DslFactory)

new SbtMicroserviceJobBuilder('employment-check-stub').
        build(this as DslFactory)


new BuildMonitorViewBuilder('APPRENTICESHIP-LEVY-MONITOR')
        .withJobs(
        'apprenticeship-levy',
        'apprenticeship-levy-stub',
        'employment-check',
        'employment-check-stub').build(this)