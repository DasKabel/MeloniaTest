package de.daskabelgaming.mysql;

import de.daskabelgaming.Main;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestQueue implements Runnable{

    private List<PreparedStatement> requests;
    private boolean running;
    private Thread thread;

    public RequestQueue() {
        this.requests = new ArrayList<PreparedStatement>();
        this.running = true;
        this.thread = new Thread(this);
    }

    public void setRunning(boolean running) {
        this.running = running;
        if (running)
            this.thread.start();
    }




    public void addToQueue(PreparedStatement qry)
    {
        this.requests.add(qry);
    }




    public void run()
    {
        while (this.running) {
            if (this.requests.size() > 0) {
                for (int i = 0; i < this.requests.size(); i++) {
                    try {
                        PreparedStatement qry = (PreparedStatement)this.requests.get(i);
                        qry.executeUpdate();
                        this.requests.remove(i);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            try
            {
                Thread.sleep(20L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
