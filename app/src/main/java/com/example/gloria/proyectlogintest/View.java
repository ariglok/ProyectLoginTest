package com.example.gloria.proyectlogintest;

/**
 * Created by gloria on 21/4/17.
 */

public interface View {

    void hideLoginForm();
    void hideLoginButton();
    void hideLogOutButton();
    
    void showError(String message);
}
