package api.mn.baby.watcher.service

import api.mn.baby.watcher.model.AppSettings
import api.mn.baby.watcher.repository.FirebaseConnection
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class NoiseServiceSpec extends Specification {
    @Inject
    NoiseService noiseService

    //todo o teste não está obtendo as informações do contexto
    void 'test getSettingsAlerts'() {
        setup:
        FirebaseConnection.initialize()

        when:
        AppSettings appSettings = noiseService.getSettingsAlerts()

        then:
        noiseService.appSettings != null
        noiseService.appSettings.soundTime == 4 ||
                noiseService.appSettings.soundTime == 5 ||
                noiseService.appSettings.soundTime == 6

    }

}
