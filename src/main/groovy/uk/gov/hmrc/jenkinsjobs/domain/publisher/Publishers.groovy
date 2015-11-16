package uk.gov.hmrc.jenkinsjobs.domain.publisher

import uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.Publisher

import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.ArtifactsPublisher.artifactsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.HtmlReportsPublisher.htmlReportsPublisher
import static uk.gov.hmrc.jenkinsjobbuilders.domain.publisher.JUnitReportsPublisher.jUnitReportsPublisher

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
        artifactsPublisher('target/dependencies/*')
    }
}
