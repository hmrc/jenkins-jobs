import javaposse.jobdsl.dsl.DslFactory
import uk.gov.hmrc.jenkinsjobbuilders.domain.ListViewBuilder
import uk.gov.hmrc.jenkinsjobs.domain.builder.SbtLibraryJobBuilder

new SbtLibraryJobBuilder('sbt-git-versioning').
                         withoutJUnitReports().
                         build(this as DslFactory)

new SbtLibraryJobBuilder('time').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('sbt-bobby').
                         build(this as DslFactory)

new SbtLibraryJobBuilder('jenkins-job-builders').
                         build(this as DslFactory)
                         
new ListViewBuilder('mdtp').
                    withJobsRegex('releaser.*').build(this as DslFactory)
