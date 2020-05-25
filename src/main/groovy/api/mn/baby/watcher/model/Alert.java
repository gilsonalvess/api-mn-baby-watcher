package api.mn.baby.watcher.model;

//A serialização para persistencia da entidade no firebase só funcionou com classe de Pojo Java.
public class Alert {
    private String uuid;
    private String date;
    private String time;

    public Alert(String uuid, String date, String time) {
        this.uuid = uuid;
        this.date = date;
        this.time = time;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Alert[" +
                "uuid='" + uuid + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ']';
    }
}