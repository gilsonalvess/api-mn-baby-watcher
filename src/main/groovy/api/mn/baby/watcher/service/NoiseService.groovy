package api.mn.baby.watcher.service

import api.mn.baby.watcher.model.Noise
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import groovy.util.logging.Slf4j
import io.micronaut.context.annotation.Context

import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalTime

@Slf4j
@Context
class NoiseService {
    private static List<LocalTime> timeList = []

    void noiseEventListener(DatabaseReference databaseReference) {

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                Noise noise = dataSnapshot.getValue(Noise.class)
                log.debug("Event {}", "${noise.date} ${noise.time}")
                LocalTime noiseTime = LocalTime.parse(noise.time.toString())
                timeList.add(noiseTime)
                if (timeList.size() == 5) {
                    noiseValidate(databaseReference)
                    timeList = []
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

    private static noiseValidate(DatabaseReference databaseReference) {
        Duration duration = Duration.between(timeList.first(), timeList.last())
        Long durationSeconds = duration.getSeconds()
        Long sizeListTime = timeList.size() - 1
        if (durationSeconds == sizeListTime) {
            log.info("Noise detected {} - Interval in Seconds {}", new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()), durationSeconds)
            databaseReference.child("alert").setValueAsync(true)
        } else {
            databaseReference.child("alert").setValueAsync(false)
        }
    }

    String teste() {
        "Teste..."
    }

}
