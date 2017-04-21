package com.example.gloria.proyectlogintest;

class FakeExecutor implements Executor{
    @Override
    public void post(Runnable run) {
        run.run();
    }
}