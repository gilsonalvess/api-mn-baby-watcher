package api.mn.baby.watcher.model

import org.joda.time.LocalDate
import org.joda.time.LocalTime

class Noise implements Serializable{
    public String date
    public String time

    Noise() {
    }

    LocalDate getDate() {
        return LocalDate.parse(date)
    }

    LocalTime getTime() {
        return LocalTime.parse(time)
    }
}
