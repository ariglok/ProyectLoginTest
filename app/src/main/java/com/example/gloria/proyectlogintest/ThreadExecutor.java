package com.example.gloria.proyectlogintest;

/**
 * Created by gloria on 21/4/17.
 */

public class ThreadExecutor implements Executor {
    @Override
    public void post(Runnable run) {
        new Thread(run).start();
    }
}
