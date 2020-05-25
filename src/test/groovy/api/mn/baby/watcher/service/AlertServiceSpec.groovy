package api.mn.baby.watcher.service


import api.mn.baby.watcher.repository.FirebaseConnection
import spock.lang.Specification

class AlertServiceSpec extends Specification {

    void 'test syncFirebaseAlert'() {
        setup:
        FirebaseConnection.initialize()

        expect:
        AlertService.syncFirebaseAlert()
    }
}
