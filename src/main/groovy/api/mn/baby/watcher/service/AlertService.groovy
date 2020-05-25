package api.mn.baby.watcher.service

import api.mn.baby.watcher.model.Alert
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import groovy.util.logging.Slf4j
import io.micronaut.context.annotation.Context
import org.joda.time.LocalDate
import org.joda.time.LocalTime


@Slf4j
@Context
class AlertService {
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()

    static void syncFirebaseAlert() {
        Alert alert = new Alert(UUID.randomUUID().toString(), LocalDate.now().toString('dd/MM/yyyy'), LocalTime.now().toString('HH:mm:ss'))

        try {
            databaseReference.child("alerts").child(alert.getUuid()).setValueAsync(alert)
            log.info("Alert saved successfully: " + alert.toString())
        } catch (Exception e) {
            log.info("Error syncFirebaseAlert alert: " + e.getMessage())
        }
    }
}
