package api.mn.baby.watcher

import api.mn.baby.watcher.service.NoiseService
import io.micronaut.runtime.Micronaut
import groovy.transform.CompileStatic

@CompileStatic
class Application {
    static void main(String[] args) {
        Micronaut.run(Application)
        FirebaseConnection.initialize()
        FirebaseConnection.initializeNoiseEventListener()
    }
}