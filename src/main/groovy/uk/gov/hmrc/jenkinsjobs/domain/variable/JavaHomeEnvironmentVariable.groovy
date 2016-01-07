package uk.gov.hmrc.jenkinsjobs.domain.variable

import uk.gov.hmrc.jenkinsjobbuilders.domain.variables.EnvironmentVariable

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variables.StringEnvironmentVariable.stringEnvironmentVariable

class JavaHomeEnvironmentVariable implements EnvironmentVariable {
    private final EnvironmentVariable environmentVariable

    JavaHomeEnvironmentVariable(JavaVersionEnvironmentVariable javaVersionEnvironmentVariable) {
        this.environmentVariable = stringEnvironmentVariable('JAVA_HOME', "/usr/lib/jvm/jdk${javaVersionEnvironmentVariable.getValue()}")
    }

    static JavaHomeEnvironmentVariable javaHomeEnvironmentVariable(JavaVersionEnvironmentVariable javaVersionEnvironmentVariable) {
        new JavaHomeEnvironmentVariable(javaVersionEnvironmentVariable)
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