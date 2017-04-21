package com.example.gloria.proyectlogintest;

/**
 * Created by gloria on 21/4/17.
 */

public class SessionApiClient {

    private final Executor executor;

    public SessionApiClient(Executor executor) {
        this.executor = executor;
    }

    public void login(final String email, final String pass, final LogInCallback calback){
        executor.post(
         new Runnable() {
            @Override
            public void run() {
                if (email.equalsIgnoreCase("GLORIA") && pass.equals("123")){
                    calback.onSuccess();

                }else{
                    calback.onError();
                }
            }
        });

    }
    public  void logout(final LogOutCallback calback){
        executor.post(new Runnable() {
            @Override
            public void run() {
                if (System.currentTimeMillis()%2 == 0){
                    calback.onSuccess();
                }else{
                    calback.onError();
                }
            }
        });

    }
    interface LogInCallback{
        void onSuccess();
        void onError();
    }
    interface  LogOutCallback{
        void onSuccess();
        void onError();
    }
}
