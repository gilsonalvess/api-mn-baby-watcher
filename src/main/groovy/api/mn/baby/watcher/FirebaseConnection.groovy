package api.mn.baby.watcher

import api.mn.baby.watcher.model.Noise
import api.mn.baby.watcher.service.NoiseService
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import groovy.util.logging.Log

import javax.inject.Singleton

@Singleton
class FirebaseConnection {

    private static DatabaseReference databaseReference

    static initialize() {
        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json")
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://baby-detector.firebaseio.com")
                    .build()

            FirebaseApp.initializeApp(options)

        }
        catch (IOException e) {
            System.out.println("ERROR: invalid service account credentials.")
            System.out.println(e.getMessage())

            System.exit(1)
        }
    }

    static initializeNoiseEventListener() {
        databaseReference = FirebaseDatabase.getInstance().getReference("noise_event")

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {}

            //todo realizar testes de chamada para o NoiseService para processamento das horas
            //todo criar lista de horas min para processar (ver qual intervalo confiável) calcular média de tempo ou som initerrupto

            @Override
            void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                Noise noise = dataSnapshot.getValue(Noise.class)
                System.out.println(noise.date + " " + noise.time)
            }

            @Override
            void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            void onCancelled(DatabaseError databaseError) {}
        })
    }
}
