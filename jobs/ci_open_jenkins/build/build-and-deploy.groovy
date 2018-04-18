import uk.gov.hmrc.jenkinsjobs.domain.builder.GradleLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.GradleLibraryReleaseJobBuilder

new GradleLibraryJobBuilder('jenkins-job-builders').
    build(this)

new GradleLibraryReleaseJobBuilder('jenkins-job-builders').
    build(this)
