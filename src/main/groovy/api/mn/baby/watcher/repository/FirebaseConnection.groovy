package api.mn.baby.watcher.repository

import api.mn.baby.watcher.service.NoiseService
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import groovy.util.logging.Slf4j

import javax.inject.Singleton

@Slf4j
@Singleton
class FirebaseConnection {

    static initialize() {
        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json")
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://baby-detector.firebaseio.com")
                    .build()

            FirebaseApp.initializeApp(options)
            log.info("FirebaseApp initialized")

        }
        catch (IOException e) {
            log.error("ERROR: invalid service account credentials.")
            log.error(e.getMessage())

            System.exit(1)
        }
    }

    static initializeNoiseEventListener() {
        NoiseService noiseService = new NoiseService()
        DatabaseReference databaseReference = databaseReferenceInstance("noise_event")
        noiseService.noiseEventListener(databaseReference)
    }

    static DatabaseReference databaseReferenceInstance(String path) {
        return FirebaseDatabase.getInstance().getReference(path)
    }
}
