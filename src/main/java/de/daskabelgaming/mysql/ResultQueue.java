package de.daskabelgaming.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultQueue implements Runnable{

    private List<PreparedStatement> requests;
    private boolean running;
    private Thread thread;
    private ResultSet resultSet;

    public ResultQueue() {
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

    public ResultSet getResult(PreparedStatement preparedStatement) {
        addToQueue(preparedStatement);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void run()
    {
        while (this.running) {
            if (this.requests.size() > 0) {
                for (int i = 0; i < this.requests.size(); i++) {
                    try {
                        PreparedStatement qry = (PreparedStatement)this.requests.get(i);
                        resultSet = qry.executeQuery();
                        this.requests.remove(i);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            try
            {
                Thread.sleep(45);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

