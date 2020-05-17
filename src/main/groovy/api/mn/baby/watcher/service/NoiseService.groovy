package api.mn.baby.watcher.service

import api.mn.baby.watcher.FirebaseMessagingSnippets
import api.mn.baby.watcher.model.AppSettings
import api.mn.baby.watcher.model.Noise
import api.mn.baby.watcher.repository.FirebaseConnection
import com.google.firebase.database.*
import groovy.util.logging.Slf4j
import io.micronaut.context.annotation.Context

import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalTime

@Slf4j
@Context
class NoiseService {
    private static List<LocalTime> timeList = []
    private static FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets()
    private static AppSettings appSettings = new AppSettings()

    static void noiseEventListener(DatabaseReference databaseReference) {
        getSettingsAlerts()
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                if (!appSettings.isDisabledAlert) {
                    Noise noise = dataSnapshot.getValue(Noise.class)
                    log.debug("Event {}", "${noise.date} ${noise.time}")
                    LocalTime noiseTime = LocalTime.parse(noise.time.toString())

                    timeList.add(noiseTime)
                    if (timeList.size() == appSettings.soundTime) {
                        noiseValidate()
                        timeList = []
                    }
                }
            }

            @Override
            void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            void onCancelled(DatabaseError databaseError) {}
        })

    }

    private static noiseValidate() {
        Duration duration = Duration.between(timeList.first(), timeList.last())
        Long durationSeconds = duration.getSeconds()
        Long sizeListTime = timeList.size() - 1
        if (durationSeconds == sizeListTime) {
            log.info("Noise detected {} - Interval in Seconds {}", new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()), durationSeconds)
            firebaseMessagingSnippets.sendNotification()
        }
    }

    private static void getSettingsAlerts() {
        DatabaseReference refSettings = FirebaseConnection.databaseReferenceInstance("settings")

        refSettings.addValueEventListener(new ValueEventListener() {
            @Override
            void onDataChange(DataSnapshot snapshot) {
                appSettings.isDisabledAlert = snapshot.value.getAt("disable_alerts")
                appSettings.soundTime = snapshot.value.getAt("sound_time") as Integer
                log.info("Settings alerts: Disabled alert={} - Sound time={}", appSettings.isDisabledAlert, appSettings.soundTime)
            }

            @Override
            void onCancelled(DatabaseError error) {}
        })
    }
}
