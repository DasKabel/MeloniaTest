package de.daskabelgaming.time;

import de.daskabelgaming.Main;
import de.daskabelgaming.mysql.database.TimeController;
import de.daskabelgaming.user.User;

import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

public class TimeHandler {

    private static final TimeController timeController = Main.getTimeController();

    private static final HashMap<User,List<TimeManager>> timeList = new HashMap<>();


    public static HashMap<User, List<TimeManager>> getTimeList() {
        return timeList;
    }

    private static boolean existsTime(User user,TimeManager timeManager) {
        List<TimeManager> times = timeList.get(user);
        if(times != null) {
            for (TimeManager time : times) {
                return time.getWorkDay() != timeManager.getWorkDay();
            }
        } else {
            return false;
        }
        return false;
    }

    public static List<TimeManager> getTimes(User user) {
        return timeList.get(user);
    }

    public static List<TimeManager> getTimesOnMonth(User user,int month) {
        List<TimeManager> monthList = new ArrayList<>();
        for(TimeManager timeManager : timeList.get(user)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(timeManager.getWorkDay());
            if(calendar.get(Calendar.MONTH)+1 == month) {
                monthList.add(timeManager);
            }
        }
        return monthList;
    }

    public static void addTime(User user,TimeManager timeManager) {
        List<TimeManager> times;
        if(timeList.get(user) == null) {
            times = new ArrayList<>();
        } else {
            times = timeList.get(user);
        }
        if(!existsTime(user,timeManager)) {
            times.add(timeManager);
            timeList.put(user, times);
        }
    }


    public static void loadTimes(User user) {
        timeController.loadWorkingTimes(user);
    }
}
