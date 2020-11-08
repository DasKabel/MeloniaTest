package de.daskabelgaming.time;

public class WorkingHours {

/*    static Database database = Main.getDatabase();
    HashMap<Integer,Calendar> start, end;
    HashMap<Integer,Long> workingTime;

    int userID;
    Calendar month;

    public WorkingHours(User user,int month) {

    }




*//*    public static void setWorkingHours(User user, String startTime, String endTime) {
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        try {
            Calendar start = Calendar.getInstance();
            start.setTime(time.parse(startTime));
            Calendar end = Calendar.getInstance();
            end.setTime(time.parse(endTime));
            database.setWorkingTime(user,start.getTime(),end.getTime(),getWorkingHours(start,end),getWorkingMinutes(start,end));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //database.setWorkingTime(user);
    }

    private static int getWorkingHours(Calendar startTime, Calendar endTime) {
        if(endTime.before(startTime)) {
            return 0;
        } return endTime.get(Calendar.HOUR) - startTime.get(Calendar.HOUR);
    }

    private static int getWorkingMinutes(Calendar startTime, Calendar endTime) {
        Calendar lunch = Calendar.getInstance();
        lunch.add(Calendar.MINUTE,30);
        if(endTime.before(startTime)) {
            return 0;
        } return (endTime.get(Calendar.MINUTE) - startTime.get(Calendar.MINUTE))- lunch.get(Calendar.MINUTE);
    }

    private static int getLunch() {

    }*//*

    public static void setWorkingTime(User user, String start, String end) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            Calendar startTime = Calendar.getInstance();
            Calendar endTime = Calendar.getInstance();
            Calendar withLunch = Calendar.getInstance();
            startTime.setTime(format.parse(start));
            endTime.setTime(format.parse(end));
            withLunch.setTime(format.parse(end));
            withLunch.add(Calendar.MINUTE, -30);
            long worked = withLunch.getTimeInMillis() - startTime.getTimeInMillis();

            database.setWorkingTime(user,startTime,endTime,worked);
        }catch (Exception exception) {exception.printStackTrace();}
    }

    public static int getWorkingHours(User user) {
        Calendar hours = database.getWorkingHours(user);
        return hours.get(Calendar.HOUR);
    }

    *//*public static int getWorkingMinutes(User user) {
        Calendar workingMinutes = database.getWorkingHours(user);
        int minutes = workingMinutes.get(Calendar.MINUTE);
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
    }*/
}
