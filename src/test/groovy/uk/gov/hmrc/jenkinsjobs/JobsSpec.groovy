package uk.gov.hmrc.jenkinsjobs

import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.dsl.MemoryJobManagement
import spock.lang.Specification
import spock.lang.Unroll

import static groovy.io.FileType.*

class JobsSpec extends Specification {

    @Unroll
    void 'test script #file.name'(File file) {
        when:
        new DslScriptLoader(new MemoryJobManagement()).runScript(file.text)

        then:
        noExceptionThrown()

        where:
        file << jobFiles
    }

    private static List<File> getJobFiles() {
        List<File> files = []
        new File('jobs').eachFileRecurse(FILES) {
            files << it
        }
        files
    }
}
