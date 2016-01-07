package uk.gov.hmrc.jenkinsjobs.domain.variable

import uk.gov.hmrc.jenkinsjobbuilders.domain.variables.EnvironmentVariable

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.StringEnvironmentVariable.stringEnvironmentVariable

enum JavaVersionEnvironmentVariable implements EnvironmentVariable {
    JDK8_40('1.8.0_40'),
    JDK8_66('1.8.0_66')

    private final EnvironmentVariable environmentVariable

    private JavaVersionEnvironmentVariable(String version) {
        this.environmentVariable = stringEnvironmentVariable('JAVA_VERSION', version)
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
