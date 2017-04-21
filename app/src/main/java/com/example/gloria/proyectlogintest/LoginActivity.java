package com.example.gloria.proyectlogintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private boolean isLogged = false;

    SessionApiClient sessionApiClient;

    Button buttonLogin;
    Button buttonLogout;
    TextView textViewEmail;
    TextView textViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionApiClient = new SessionApiClient(new ThreadExecutor());

        buttonLogin = (Button) findViewById(R.id.btnLogin);
        buttonLogout = (Button) findViewById(R.id.btnLogout);
        textViewEmail = (TextView) findViewById(R.id.email);
        textViewPassword = (TextView) findViewById(R.id.password);

        refreshButton();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }
    public void login(){
        sessionApiClient.login(textViewEmail.getText().toString(), textViewPassword.getText().toString(), new SessionApiClient.LogInCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(),"Login Correcto",Toast.LENGTH_SHORT).show();
                isLogged = true;
                textViewClear();
                refreshButton();
            }

            @Override
            public void onError() {
                Toast.makeText(getApplicationContext(),"Login Incorrecto",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void logout(){
        sessionApiClient.logout(new SessionApiClient.LogOutCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(),"Logout Correcto",Toast.LENGTH_SHORT).show();
                isLogged = false;
                textViewClear();
                refreshButton();
            }

            @Override
            public void onError() {
                Toast.makeText(getApplicationContext(),"Logout Incorrecto",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void refreshButton(){
        if (isLogged){
            buttonLogin.setVisibility(View.GONE);
            buttonLogout.setVisibility(View.VISIBLE);
        }else{
            buttonLogin.setVisibility(View.VISIBLE);
            buttonLogout.setVisibility(View.GONE);

        }
    }

    public void textViewClear(){
        textViewEmail.setText("");
        textViewPassword.setText("");
    }

    public void showEmailPass(Boolean show){
        if (show){
            textViewEmail.setVisibility(View.VISIBLE);
            textViewPassword.setVisibility(View.VISIBLE);
        }else{
            textViewEmail.setVisibility(View.GONE);
            textViewPassword.setVisibility(View.GONE);
        }

    }

}
