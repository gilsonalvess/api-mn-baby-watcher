package api.mn.baby.watcher.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller('/')
class HelloController {

    @Get(produces = MediaType.APPLICATION_JSON)
    String index() {
        'Hello World'
    }
}
