package de.daskabelgaming.time;

import de.daskabelgaming.Main;
import de.daskabelgaming.mysql.database.TimeController;
import de.daskabelgaming.user.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;

public class TimeManager {

    private final TimeController timeController = new TimeController();

    private final HashMap<Date,Calendar> startWorkDay, StopWorkDay;
    private final HashMap<Date,Integer> hoursWorkDay, minutesWorkDay;

    User user;
    int hours, minutes;
    Calendar workStart,workStop;
    Date workDay;

    //Pushing Workday to Database
    public TimeManager(User user, Calendar workStart, Calendar workStop) {

        startWorkDay = new HashMap<>();
        StopWorkDay = new HashMap<>();
        hoursWorkDay = new HashMap<>();
        minutesWorkDay = new HashMap<>();

        this.user = user;
        this.workStart = workStart;
        this.workStop = workStop;
        this.workDay = Date.valueOf(LocalDate.now());

        startWorkDay.put(this.workDay,this.workStart);
        StopWorkDay.put(this.workDay,this.workStop);
        toDatabase();
    }
    //Loading Workday from Database
    public TimeManager(User user, Calendar workStart, Calendar workStop, Date workDay, int hours, int minutes) {
        //TODO WorkDay Exists

        startWorkDay = new HashMap<>();
        StopWorkDay = new HashMap<>();
        hoursWorkDay = new HashMap<>();
        minutesWorkDay = new HashMap<>();

        this.user = user;
        this.workStart = workStart;
        this.workStop = workStop;
        this.workDay = workDay;
        this.hours = hours;
        this.minutes = minutes;

        startWorkDay.put(this.workDay,this.workStart);
        StopWorkDay.put(this.workDay,this.workStop);
        hoursWorkDay.put(workDay,hours);
        minutesWorkDay.put(workDay,roundMinutes(minutes));

    }

    private int roundMinutes(int minutes) {
        if(minutes < 15) {
            minutes = 0;
        } else if(minutes <30 && minutes > 15) {
            minutes = 15;
        } else if(minutes < 45 && minutes > 30) {
            minutes = 30;
        } else {
            minutes = 45;
        }
        return minutes;
    }

    public Date getWorkDay() {
        return workDay;
    }

    public void setWorkDay(Date date) {
        this.workDay = date;
    }

    public Calendar getWorkStart() {
        return workStart;
    }

    public void setWorkStart(Calendar workStart) {
        this.workStart = workStart;
    }

    public Calendar getWorkStop() {
        return workStop;
    }

    public void setWorkStop(Calendar workStop) {
        this.workStop = workStop;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public User getUser() {
        return user;
    }

    //Pushing to Database
    private void toDatabase() {
        if(!timeController.existsTime(this.user,this.workDay)) {
            Calendar withBreak = Calendar.getInstance();
            withBreak.setTimeInMillis(workStop.getTimeInMillis());
            withBreak.add(Calendar.MINUTE, -30);
            long duration = withBreak.getTimeInMillis() - workStart.getTimeInMillis();
            timeController.setWorkingTime(this.user, workStart, this.workStop, duration);
            TimeHandler.addTime(this.user,this);
            System.out.println("Zeit erfolgreich gesetzt");
        }
    }
}
