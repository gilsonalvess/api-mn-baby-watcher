package api.mn.baby.watcher.controller

import api.mn.baby.watcher.service.NoiseService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

import javax.inject.Inject

@Controller("/noise")
class NoiseController {

    @Inject
    protected NoiseService noiseService

    @Get
    String index() {
        noiseService.testeString()
    }
}
