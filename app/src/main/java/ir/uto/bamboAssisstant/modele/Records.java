package ir.uto.bamboAssisstant.modele;

public class Records {
    private int id;
    private long recordedTime;
    private String title;
    private String stringTime;
    private String recordsDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getRecordedTime() {
        return recordedTime;
    }

    public void setRecordedTime(long recordedTime) {
        this.recordedTime = recordedTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStringTime() {
        return stringTime;
    }

    public void setStringTime(String stringTime) {
        this.stringTime = stringTime;
    }

    public String getRecordsDate() {
        return recordsDate;
    }

    public void setRecordsDate(String recordsDate) {
        this.recordsDate = recordsDate;
    }
}
