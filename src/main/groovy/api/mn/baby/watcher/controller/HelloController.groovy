package api.mn.baby.watcher.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller('/')
class HelloController {

    @Get(produces = MediaType.APPLICATION_JSON)
    String index() {
        //Todo implementar retorno com informações da api (nome do serviço e versão)
        'Hello World'
    }
}
