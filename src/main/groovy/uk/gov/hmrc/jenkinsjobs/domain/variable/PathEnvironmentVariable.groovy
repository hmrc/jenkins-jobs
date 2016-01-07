package uk.gov.hmrc.jenkinsjobs.domain.variable

import uk.gov.hmrc.jenkinsjobbuilders.domain.variables.EnvironmentVariable

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.StringEnvironmentVariable.stringEnvironmentVariable

class PathEnvironmentVariable implements EnvironmentVariable {
    private final EnvironmentVariable environmentVariable

    private PathEnvironmentVariable(JavaHomeEnvironmentVariable javaHomeEnvironmentVariable) {
        this.environmentVariable = stringEnvironmentVariable('PATH', "${javaHomeEnvironmentVariable.getValue()}/bin:/opt/sbt/bin:\$PATH")
    }

    static PathEnvironmentVariable pathEnvironmentVariable(JavaHomeEnvironmentVariable javaHomeEnvironmentVariable) {
        new PathEnvironmentVariable(javaHomeEnvironmentVariable)
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
