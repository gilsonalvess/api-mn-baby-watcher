package api.mn.baby.watcher.model

class AppSettings {
    Boolean isDisabledAlert
    Integer soundTime

    AppSettings() {
        this.isDisabledAlert = false
        this.soundTime = 5 // default value
    }
}
