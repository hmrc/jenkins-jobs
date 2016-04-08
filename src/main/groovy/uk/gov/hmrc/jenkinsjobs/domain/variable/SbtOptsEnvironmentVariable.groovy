package uk.gov.hmrc.jenkinsjobs.domain.variable

import uk.gov.hmrc.jenkinsjobbuilders.domain.variable.EnvironmentVariable

import static uk.gov.hmrc.jenkinsjobbuilders.domain.variable.StringEnvironmentVariable.stringEnvironmentVariable

enum SbtOptsEnvironmentVariable implements EnvironmentVariable {
    JAVA_PROXY_QUOTED('-Dhttps.proxyHost=999.999.999.999 -Dhttps.proxyPort=3128 -Dhttp.proxyHost=999.999.999.999 -Dhttp.proxyPort=3128 -Dhttp.nonProxyHosts=\'localhost|127.0.0.1\''),
    SBT_PROXY('-Dhttps.proxyHost=999.999.999.999 -Dhttps.proxyPort=3128 -Dhttp.proxyHost=999.999.999.999 -Dhttp.proxyPort=3128 -Dhttp.nonProxyHosts=localhost|127.0.0.1')

    private final EnvironmentVariable environmentVariable

    private SbtOptsEnvironmentVariable(String value) {
        this.environmentVariable = stringEnvironmentVariable('SBT_OPTS', value)
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
