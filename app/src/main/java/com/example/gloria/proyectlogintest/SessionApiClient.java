package com.example.gloria.proyectlogintest;

/**
 * Created by gloria on 21/4/17.
 */

public class SessionApiClient {
    public void login(String email, String pass, LogInCallback calback){
        if (email.equalsIgnoreCase("GLORIA") && pass.equals("123")){
            calback.onSuccess();

        }else{
            calback.onError();
        }
    }
    public  void logout(LogOutCallback calback){
        if (System.currentTimeMillis()%2 == 0){
            calback.onSuccess();
        }else{
            calback.onError();
        }
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
