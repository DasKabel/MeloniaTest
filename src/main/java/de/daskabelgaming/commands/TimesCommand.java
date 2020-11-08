package de.daskabelgaming.commands;

import de.daskabelgaming.time.TimeHandler;
import de.daskabelgaming.time.TimeManager;
import de.daskabelgaming.user.User;
import de.daskabelgaming.user.UserHandler;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimesCommand implements Runnable{

    List<TimeManager> times;
    Calendar workStart,workStop;
    Date workDay;
    int hours, minutes;

    public TimesCommand(String username) {
        if(UserHandler.isUserRegistered(username)) {
            User user = UserHandler.getUser(username);
            TimeHandler.loadTimes(user);
            try {
                Thread.sleep(25L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            times = TimeHandler.getTimes(user);
            System.out.println("####################################################\n" +
                    "# Arbeitstag # Start Zeit # End Zeit # Arbeitszeit #\n" +
                    "#            #            #          #             #");
            run();
            System.out.println("####################################################");
        } else {
            System.out.println("Dieser Benutzer exestiert nicht");
        }

    }

    public TimesCommand(String username, int month) {
        if(UserHandler.isUserRegistered(username)) {
            User user = UserHandler.getUser(username);
            TimeHandler.loadTimes(user);
            try {
                Thread.sleep(25L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            times = TimeHandler.getTimesOnMonth(user,month);
            if(times.size() == 0) {
                System.out.println("Keinen Eintrag für diesen Monat");
            } else {
                System.out.println("####################################################\n" +
                        "# Arbeitstag # Start Zeit # End Zeit # Arbeitszeit #\n" +
                        "#            #            #          #             #");
                run();
                System.out.println("####################################################");
            }
        } else {
            System.out.println("Dieser Benutzer exestiert nicht");
        }
    }

    @Override
    public void run() {
        for(TimeManager time: times) {
            this.workDay = time.getWorkDay();
            this.workStart = time.getWorkStart();
            this.workStop = time.getWorkStop();
            String startHour = String.format("%02d",workStart.get(Calendar.HOUR_OF_DAY));
            String startMinute = String.format("%02d",workStart.get(Calendar.MINUTE));

            String stopHour = String.format("%02d",workStop.get(Calendar.HOUR_OF_DAY));
            String stopMinute = String.format("%02d",workStop.get(Calendar.MINUTE));
            this.hours = time.getHours();
            this.minutes = time.getMinutes();
            String workHours;
            workHours = String.format("%02d", hours);
            String workMinutes = String.format("%02d",minutes);
            System.out.println("# "+workDay+" #    "+startHour+":"+startMinute+"   #   "+stopHour+":"+stopMinute+
                    "  #    " +workHours+":"+workMinutes+"    #");
        }
    }
}
