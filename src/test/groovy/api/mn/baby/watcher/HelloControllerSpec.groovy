package api.mn.baby.watcher

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification
import javax.inject.Inject

@MicronautTest
class HelloControllerSpec extends Specification {
    @Inject
    EmbeddedServer embeddedServer

    @Inject
    @Client("/")
    HttpClient client

    void "test hello world response"() {
        expect:
        client.toBlocking()
                .retrieve(HttpRequest.GET('/hello')) == "Hello World"
    }
}