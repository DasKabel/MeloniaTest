package de.daskabelgaming.time;

import de.daskabelgaming.user.User;
import de.daskabelgaming.user.UserHandler;

import java.text.SimpleDateFormat;
import java.util.*;

public class AutoTime extends TimerTask {

    Calendar calendar = Calendar.getInstance();


    public AutoTime() {
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,50);
        calendar.set(Calendar.SECOND,0);
        Date alarm = calendar.getTime();

        Timer _timer = new Timer();
        _timer.schedule(this,alarm);
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Calendar start = Calendar.getInstance();
        Calendar stop = Calendar.getInstance();
        try {
            start.setTime(sdf.parse("00:00:00"));
            stop.setTime(sdf.parse("00:30:00"));
        }catch (Exception exception) {exception.printStackTrace();}
        for(Map.Entry<String,User> userEntry : UserHandler.getUsers().entrySet()) {
            User value = userEntry.getValue();
            new TimeManager(value,start,stop);
            System.out.println("Zeiten für alle Nutzer aufgefüllt");
        }
    }
}
