package de.daskabelgaming.mysql.database;

import de.daskabelgaming.Main;
import de.daskabelgaming.mysql.MySQL;
import de.daskabelgaming.time.TimeHandler;
import de.daskabelgaming.time.TimeManager;
import de.daskabelgaming.user.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Calendar;

public class TimeController {

    MySQL mySQL = Main.getMySQL();

    public boolean existsTime(User user, Date date) {
        String sql = "SELECT working_hours FROM time WHERE (user_id,date) = (?,?)";
        try {
            PreparedStatement  statement = MySQL.getConnection().prepareStatement(sql);
            statement.setInt(1,user.getId());
            statement.setDate(2,date);
            if(mySQL.result(statement) != null) {
                return mySQL.result(statement).next();
            } else return false;
        }catch (Exception exception) {exception.printStackTrace();}
        return false;
    }

    public void setWorkingTime(User user, Calendar start, Calendar end,long worked) {
        String sql = "INSERT INTO time (user_id,date,start_time,end_time,working_hours) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement statement = MySQL.getConnection().prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.setDate(2, Date.valueOf(LocalDate.now()));
            statement.setTime(3, new Time(start.getTimeInMillis()));
            statement.setTime(4, new Time(end.getTimeInMillis()));
            statement.setLong(5, worked);

            //int workingHours = (startTime.get(Calendar.HOUR) - endTime.get(Calendar.HOUR));
            //statement.setInt(5,workingHours);
            mySQL.update(statement);


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public void loadWorkingTimes(User user) {
        String sql = "SELECT date,start_time,end_time,working_hours FROM time WHERE user_id = ?";
        try {
            PreparedStatement statement = MySQL.getConnection().prepareStatement(sql);
            statement.setInt(1,user.getId());
            ResultSet resultSet = mySQL.result(statement);
            while (resultSet.next()) {
                Calendar start = Calendar.getInstance();
                Calendar stop = Calendar.getInstance();
                Calendar duration = Calendar.getInstance();
                Date date = resultSet.getDate("date");
                start.setTime(resultSet.getTime("start_time"));
                stop.setTime(resultSet.getTime("end_time"));
                long dur = resultSet.getLong("working_hours");
                duration.setTimeInMillis(dur);
                int hour  = duration.get(Calendar.HOUR) -1;
                int minutes  = duration.get(Calendar.MINUTE);
                TimeHandler.addTime(user,new TimeManager(user,start,stop,date,hour,minutes));
            }
        }catch (Exception exception) {exception.printStackTrace();}
    }
}
