package ci_open_jenkins.build

import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtLibraryJobBuilder('bulk-entity-streaming').
        build(this)

