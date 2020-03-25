package com.model.utilities;

import java.io.Serializable;
import java.util.Objects;

public class Hour implements Serializable {

    private static final long serialVersionUID = 2681531852204068105L;
    public String minute;
    public String hour;

    public Hour(int hour, int minute) {
        this.hour = Integer.toString(hour);
        this.minute = Integer.toString(minute);
    }

    public Hour(String hour, String minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public Hour(String hour) {
        if (hour.contains(":")) {
            String[] stringAux = hour.split(":");
            this.hour = stringAux[0];
            this.minute = stringAux[1];
        }
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getMinuteInt() {
        return Integer.parseInt(minute);
    }

    public void setMinute(int minute) {
        this.minute = Integer.toString(minute);
    }

    public int getHourInt() {
        return Integer.parseInt(hour);
    }

    public void setHour(int hour) {
        this.hour = Integer.toString(hour);
    }

    public String getTime(){
        return hour+":"+minute;
    }

    public int compare(String h1, String h2){
        String[] stringAux;
        if (h1.contains(":") && h2.contains(":")) {
            stringAux = h1.split(":");
            int h1h = Integer.parseInt(stringAux[0]);
            int h1m = Integer.parseInt(stringAux[1]);
            stringAux = h2.split(":");
            int h2h = Integer.parseInt(stringAux[0]);
            int h2m = Integer.parseInt(stringAux[1]);

            if(h1h > h2h) return 1;
            if(h1h < h2h) return 2;
            else{
                if(h1m > h2m) return 1;
                if(h1m < h2m) return 2;
                else return 0;
            }
        }else return -1; //ERROR
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hour hour1 = (Hour) o;
        return Objects.equals(minute, hour1.minute) &&
                Objects.equals(hour, hour1.hour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minute, hour);
    }
}
