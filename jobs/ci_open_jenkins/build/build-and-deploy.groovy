import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.BuildMonitorViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.GradleLibraryJobBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.GradleLibraryReleaseJobBuilder

new GradleLibraryJobBuilder('jenkins-job-builders').
    build(this)

new GradleLibraryReleaseJobBuilder('jenkins-job-builders').
    build(this)

new BuildMonitorViewBuilder("BUILD-AND-DEPLOY")
    .withJobs("jenkins-job-builders", "jenkins-job-builders-release")
    .build(this)
