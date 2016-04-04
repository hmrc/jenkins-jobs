package uk.gov.hmrc.jenkinsjobs.domain.publisher

import uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.Publisher

import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.ArtifactsPublisher.artifactsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.BuildDescriptionPublisher.buildDescriptionByRegexPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.HtmlReportsPublisher.htmlReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.JUnitReportsPublisher.jUnitReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.PostBuildTaskPublisher.postBuildTaskPublisher

final class Publishers {

    private Publishers() {
    }

    static Publisher defaultJUnitReportsPublisher() {
        jUnitReportsPublisher('target/*test-reports/*.xml')
    }

    static Publisher defaultHtmlReportsPublisher() {
        htmlReportsPublisher(['target/test-reports/html-report': 'HTML Report', 'target/int-test-reports/html-report' : 'IT HTML Report'])
    }

    static Publisher bobbyArtifactsPublisher() {
        artifactsPublisher('target/bobby-reports/*')
    }

    static Publisher defaultBuildDescriptionPublisher() {
        buildDescriptionByRegexPublisher('.*sbt git versioned as ([\\w\\d\\.\\-]+)')
    }

    static Publisher cleanXvfbPostBuildTaskPublisher() {
        return postBuildTaskPublisher('Xvfb starting(.*)', """\
                                                           |#!/bin/bash
                                                           |ps cax | grep Xvfb > /dev/null
                                                           |if [ \$? -eq 0 ]; then
                                                           |  echo "Cleaning up Xvfb"
                                                           |  pkill Xvfb
                                                           |  if [ -e /tmp/.X99-lock ]
                                                           |  then
                                                           |    unlink /tmp/.X99-lock
                                                           |  fi
                                                           |fi
                                                           """.stripMargin())
    }

}
