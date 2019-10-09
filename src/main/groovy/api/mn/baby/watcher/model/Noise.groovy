package api.mn.baby.watcher.model

class Noise implements Serializable{
    public String date
    public String time

    Noise() {
    }

    Noise(String date, String time) {
        this.date = date
        this.time = time
    }
}
