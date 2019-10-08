package api.mn.baby.watcher.service

import api.mn.baby.watcher.model.NoiseFilter
import io.micronaut.runtime.context.scope.Refreshable

import java.time.LocalTime

@Refreshable
class NoiseService implements NoiseFilter {

    @Override
    LocalTime getTimeInterval() {
        return null
    }

    @Override
    Boolean validateNoise() {
        return true
    }

    String testeString(){
        "Testando..."
    }

}
