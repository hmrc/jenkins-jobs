package uk.gov.hmrc.jenkinsjobs.domain.variable

import uk.gov.hmrc.jenkinsjobbuilders.domain.variable.EnvironmentVariable

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variable.StringEnvironmentVariable.stringEnvironmentVariable

class ClasspathEnvironmentVariable implements EnvironmentVariable {
    private final EnvironmentVariable environmentVariable = stringEnvironmentVariable('CLASSPATH', '${CLASSPATH}:/opt/sbt/bin')

    private ClasspathEnvironmentVariable() {}

    static ClasspathEnvironmentVariable classpathEnvironmentVariable() {
        new ClasspathEnvironmentVariable()
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
