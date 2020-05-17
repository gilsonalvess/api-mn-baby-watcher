package api.mn.baby.watcher

import com.google.firebase.messaging.*
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

    void sendNotification() throws FirebaseMessagingException {
        String response = FirebaseMessaging.getInstance().send(androidMessage())
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

    Message androidMessage() {
        Message message = Message.builder()
                .setAndroidConfig(AndroidConfig.builder()
                        .setPriority(AndroidConfig.Priority.HIGH)
                        .setNotification(AndroidNotification.builder()
                                .setTitle("Baby Alert")
                                .setBody("Seu bebê precisa de você!")
                                .setIcon("baby_crying_icon")
                        //.setColor("#f45342")
                                .build())
                        .build())
                .setTopic("detector")
                .build()

        return message
    }

}
