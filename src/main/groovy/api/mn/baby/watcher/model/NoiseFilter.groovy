package api.mn.baby.watcher.model

import java.time.LocalTime

interface NoiseFilter {
    LocalTime getTimeInterval()
    Boolean noiseValidate()
}