package com.example.luigidigirolamo.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luigidigirolamo on 18/11/15.
 */
public class Event {
    private String title;
    private String startdate;
    private String enddate;

    public Event (String title, String startdate, String enddate) {
        String format = new String ("MM-dd HH:mm");

        Date datestart = null;
        Date dateend = null;
        try {
            datestart = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(startdate.substring(0,startdate.length()-1));
            dateend = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(enddate.substring(0,enddate.length()-1));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        startdate = new SimpleDateFormat("dd-MM HH:mm").format(datestart);
        enddate = new SimpleDateFormat("dd-MM HH:mm").format(dateend);

        this.title = title;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStartdate() {
        return startdate;
    }
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }
    public String getEnddate() {
        return enddate;
    }
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
