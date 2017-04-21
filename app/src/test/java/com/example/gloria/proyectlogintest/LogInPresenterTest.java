package com.example.gloria.proyectlogintest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogInPresenterTest {

    private LoginPresenter presenter;

    @Mock
    private SessionApiClient apiClient;
    @Mock
    private LogInView view;

    @Before
    public void setUp() {
        presenter = new LoginPresenter(apiClient, view);
    }

    @Test
    public void showsErrorIfTheUserAndPassAreEmptyOnLogInButtonClick() {
        ArgumentCaptor<SessionApiClient.LogInCallback> argumentCaptor = ArgumentCaptor.forClass(SessionApiClient.LogInCallback.class);

        presenter.onLogInButtonClick("", "");
        verify(apiClient).login(anyString(), anyString(), argumentCaptor.capture());
        argumentCaptor.getValue().onError();

        verify(view).showMessage("Login Incorrecto");
    }

    @Test
    public void showsSuccessIfTheLogInIsCorrect() {
        ArgumentCaptor<SessionApiClient.LogInCallback> argumentCaptor = ArgumentCaptor.forClass(SessionApiClient.LogInCallback.class);

        presenter.onLogInButtonClick("pedro", "pedro");
        verify(apiClient).login(eq("pedro"), eq("pedro"), argumentCaptor.capture());
        argumentCaptor.getValue().onError();

        verify(view).showMessage("Login Incorrecto");
    }

    @Test
    public void showsLogoutErrorIfTheLogOutIsNotSuccess() {
        ArgumentCaptor<SessionApiClient.LogOutCallback> argumentCaptor = ArgumentCaptor.forClass(SessionApiClient.LogOutCallback.class);

        presenter.onLogOutButtonClick();
        verify(apiClient).logout(argumentCaptor.capture());
        argumentCaptor.getValue().onError();

        verify(view).showMessage("Logout Incorrecto");
    }

    @Test
    public void showsLogOutSuccessIfTheLogOutIsSuccess() {
        ArgumentCaptor<SessionApiClient.LogOutCallback> argumentCaptor = ArgumentCaptor.forClass(SessionApiClient.LogOutCallback.class);

        presenter.onLogOutButtonClick();
        verify(apiClient).logout(argumentCaptor.capture());
        argumentCaptor.getValue().onSuccess();

        verify(view).showMessage("Logout Correcto");
    }
}
