package api.mn.baby.watcher

import api.mn.baby.watcher.repository.FirebaseConnection
import groovy.transform.CompileStatic
import io.micronaut.runtime.Micronaut

@CompileStatic
class Application {
    static void main(String[] args) {
        Micronaut.run(Application)
        FirebaseConnection.initialize()
        FirebaseConnection.initializeNoiseEventListener()
    }
}