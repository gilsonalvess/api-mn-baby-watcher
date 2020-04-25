package api.mn.baby.watcher

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import groovy.util.logging.Slf4j

@Slf4j
class FirebaseMessagingSnippets {

    void sendToToken() throws FirebaseMessagingException {
        String registrationToken = new File("src/main/resources/tokenApp.txt").text

        Message message = Message.builder()
                .putData("babyAlert", "choro detectado!")
                .setNotification(new Notification("Baby Alert", "Seu bebê está precisando de você!"))
                .setToken(registrationToken)
                .build()

        String response = FirebaseMessaging.getInstance().send(message)
        log.info("Successfully sent message: " + response)

    }

    void sendToTopic() throws FirebaseMessagingException {
        String topic = "topics/topic"

        Message message = Message.builder()
                .putData("key", "value")
                .setTopic(topic)
                .build()

        String response = FirebaseMessaging.getInstance().send(message)
        log.info("Successfully sent message: " + response)
    }

}
