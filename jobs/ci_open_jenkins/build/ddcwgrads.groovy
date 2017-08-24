import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtMicroserviceJobBuilder

new SbtMicroserviceJobBuilder('ddcw_grads_01_backend').
        build(this as DslFactory)
new SbtMicroserviceJobBuilder('ddcw_grads_02_backend').
        build(this as DslFactory)
new SbtMicroserviceJobBuilder('ddcw_grads_01_frontend').
        build(this as DslFactory)
new SbtMicroserviceJobBuilder('ddcw_grads_01_frontend').
        build(this as DslFactory)
