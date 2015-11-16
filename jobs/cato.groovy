import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtLibraryJobBuilder('play-events').
        build(this as DslFactory)

new SbtLibraryJobBuilder('attachments-client').
        build(this as DslFactory)

new SbtLibraryJobBuilder('ct-calculations').
        build(this as DslFactory)
