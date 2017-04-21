package com.example.gloria.proyectlogintest;

public class LoginPresenter {

    private final SessionApiClient apiClient;
    private final LogInView view;

    public LoginPresenter(SessionApiClient apiClient, LogInView view) {
        this.apiClient = apiClient;
        this.view = view;
    }

    public void initialize() {

    }

    public void onLogInButtonClick(String user, String pass) {
        login(user, pass);
    }

    public void onLogOutButtonClick() {
        logout();
    }

    private void login(String email, String pass) {
        apiClient.login(email, pass, new SessionApiClient.LogInCallback() {
            @Override
            public void onSuccess() {
                view.showMessage("Login Correcto");
                view.textViewClear();
                view.showLogOutForm();
            }

            @Override
            public void onError() {
                view.showMessage("Login Incorrecto");
            }
        });
    }

    private void logout() {
        apiClient.logout(new SessionApiClient.LogOutCallback() {
            @Override
            public void onSuccess() {
                view.showMessage("Logout Correcto");
                view.textViewClear();
                view.showLogInForm();
            }

            @Override
            public void onError() {
                view.showMessage("Logout Incorrecto");
            }
        });
    }
}
