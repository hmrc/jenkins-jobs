package uk.gov.hmrc.jenkinsjobs.domain.step

import uk.gov.hmrc.jenkinsjobbuilders.domain.step.Step


class CopyUpstreamArtifactsStep implements Step {
    private final String upstreamJobName
    private final String upstreamBuildNumber
    private final String upstreamTargetDirectory
    private final String upstreamTargetArtifacts
    private String whichBuild

    private CopyUpstreamArtifactsStep(String upstreamJobName,
                                      String upstreamBuildNumber,
                                      String upstreamTargetArtifacts,
                                      String upstreamTargetDirectory,
                                      String whichBuild) {
        this.upstreamJobName = upstreamJobName
        this.upstreamBuildNumber = upstreamBuildNumber
        this.upstreamTargetArtifacts = upstreamTargetArtifacts
        this.upstreamTargetDirectory = upstreamTargetDirectory
        this.whichBuild = whichBuild
    }

    static Step copyUpstreamArtifacts(String upstreamJobName,
                                      String upstreamBuildNumber,
                                      String upstreamTargetArtifacts,
                                      String upstreamTargetDirectory,
                                      String whichBuild) {
        new CopyUpstreamArtifactsStep(upstreamJobName, upstreamBuildNumber, upstreamTargetArtifacts, upstreamTargetDirectory, whichBuild)
    }

    @Override
    Closure toDsl() {

        switch (whichBuild) {
            case "Latest successful build":
                return {
                    copyArtifacts(this.upstreamJobName, this.upstreamTargetArtifacts, this.upstreamTargetDirectory, false, false) {
                        latestSuccessful()
                    }
                }
            case "Upstream build that triggered this job":
                return {
                    copyArtifacts(this.upstreamJobName, this.upstreamTargetArtifacts, this.upstreamTargetDirectory, true, false) {
                        upstreamBuild(fallback = true)
                    }
                }
            case "Specific build":
                return {
                    copyArtifacts(this.upstreamJobName, this.upstreamTargetArtifacts, this.upstreamTargetDirectory, false, false) {
                        buildNumber(this.upstreamBuildNumber)
                    }
                }
            default:
                return {
                    copyArtifacts(this.upstreamJobName, this.upstreamTargetArtifacts, this.upstreamTargetDirectory, false, false) {
                        buildNumber(this.upstreamBuildNumber)
                    }
                }
        }
    }
}
