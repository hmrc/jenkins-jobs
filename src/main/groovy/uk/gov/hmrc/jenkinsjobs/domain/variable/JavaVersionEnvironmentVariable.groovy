package uk.gov.hmrc.jenkinsjobs.domain.variable

import uk.gov.hmrc.jenkinsjobbuilders.domain.variable.EnvironmentVariable

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variable.StringEnvironmentVariable.stringEnvironmentVariable

enum JavaVersionEnvironmentVariable implements EnvironmentVariable {
    JDK8_74('1.8.0_74')

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
