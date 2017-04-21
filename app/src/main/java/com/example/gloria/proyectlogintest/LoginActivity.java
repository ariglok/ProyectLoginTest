package com.example.gloria.proyectlogintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements LogInView {

    private LoginPresenter presenter;

    Button buttonLogin;
    Button buttonLogout;
    TextView textViewEmail;
    TextView textViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(new SessionApiClient(new ThreadExecutor(), new Clock()), this);
        buttonLogin = (Button) findViewById(R.id.btnLogin);
        buttonLogout = (Button) findViewById(R.id.btnLogout);
        textViewEmail = (TextView) findViewById(R.id.email);
        textViewPassword = (TextView) findViewById(R.id.password);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLogInButtonClick(textViewEmail.getText().toString(), textViewPassword.getText().toString());
            }
        });
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLogOutButtonClick();
            }
        });
    }


    @Override
    public void showMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showLogInForm() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewEmail.setVisibility(View.VISIBLE);
                textViewPassword.setVisibility(View.VISIBLE);
                buttonLogin.setVisibility(View.VISIBLE);
                buttonLogout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showLogOutForm() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewEmail.setVisibility(View.GONE);
                textViewPassword.setVisibility(View.GONE);
                buttonLogin.setVisibility(View.GONE);
                buttonLogout.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void textViewClear() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewEmail.setText("");
                textViewPassword.setText("");
            }
        });

    }

}
