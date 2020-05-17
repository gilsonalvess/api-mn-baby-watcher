package api.mn.baby.watcher

import api.mn.baby.watcher.repository.FirebaseConnection
import api.mn.baby.watcher.service.NoiseService
import com.google.firebase.database.DatabaseReference
import spock.lang.Specification

class NoiseServiceSpec extends Specification {

    void 'test connection firebase'() {
        setup:
        NoiseService noiseService = NoiseService.newInstance()
        FirebaseConnection.initialize()
        FirebaseConnection.initializeNoiseEventListener()
        DatabaseReference databaseReference = FirebaseConnection.databaseReferenceInstance("noise_event")

        when:
        noiseService.noiseEventListener(databaseReference)

        then:

        databaseReference.repo != null
        databaseReference.repo.repoInfo.host == 'baby-detector.firebaseio.com'
        databaseReference.repo.repoInfo.namespace == 'baby-detector'

    }

}
