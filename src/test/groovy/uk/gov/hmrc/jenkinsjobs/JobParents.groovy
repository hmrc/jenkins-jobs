package uk.gov.hmrc.jenkinsjobs

import javaposse.jobdsl.dsl.JobParent
import javaposse.jobdsl.dsl.MemoryJobManagement

class JobParents {

    static JobParent jobParent() {
        JobParent jobParent = new JobParent() {
            @Override
            Object run() {
                return null
            }
        }

        jobParent.setJm(new MemoryJobManagement())
        jobParent
    }
}
