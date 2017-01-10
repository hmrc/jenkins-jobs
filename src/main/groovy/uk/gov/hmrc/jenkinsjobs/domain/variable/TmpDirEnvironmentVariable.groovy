package uk.gov.hmrc.jenkinsjobs.domain.variable

import uk.gov.hmrc.jenkinsjobbuilders.domain.variable.EnvironmentVariable

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variable.StringEnvironmentVariable.stringEnvironmentVariable

class TmpDirEnvironmentVariable implements EnvironmentVariable {
    private final EnvironmentVariable environmentVariable = stringEnvironmentVariable('TMP', '${WORKSPACE}/tmp')

    private TmpDirEnvironmentVariable() {}

    static TmpDirEnvironmentVariable tmpDirEnvironmentVariable() {
        new TmpDirEnvironmentVariable()
    }

    @Override
    String getName() {
        environmentVariable.name
    }

    @Override
    String getValue() {
        environmentVariable.value
    }
}
