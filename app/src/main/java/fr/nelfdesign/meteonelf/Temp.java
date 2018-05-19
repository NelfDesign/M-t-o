package fr.nelfdesign.meteonelf;

import java.io.Serializable;

public class Temp implements Serializable{

    int unix;
    String date;
    String dt_text;

    public Temp(int unix, String date, String dt_text) {
        this.unix = unix;
        this.date = date;
        this.dt_text = dt_text;
    }

    public String getDt_text() {
        return dt_text;
    }

    public void setDt_text(String dt_text) {
        this.dt_text = dt_text;
    }

    public int getUnix() {
        return unix;
    }

    public void setUnix(int unix) {
        this.unix = unix;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
