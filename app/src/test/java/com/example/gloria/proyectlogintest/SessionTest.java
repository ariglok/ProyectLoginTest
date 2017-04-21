package com.example.gloria.proyectlogintest;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by gloria on 21/4/17.
 */

public class SessionTest {


    @Test
    public void returnErrorIfTjeUserIfEmpty() throws Exception {
        SessionApiClient.LogInCallback callback = mock(SessionApiClient.LogInCallback.class);
        SessionApiClient sessionApiClient = new SessionApiClient(new FakeExecutor(), new Clock());
        sessionApiClient.login("", "123", callback);

        verify(callback).onError();
        verify(callback, never()).onSuccess();

    }

    @Test
    public void returErrorIfPassIncorrect() throws Exception {
        SessionApiClient.LogInCallback callback = mock(SessionApiClient.LogInCallback.class);
        SessionApiClient sessionApiClient = new SessionApiClient(new FakeExecutor(), new Clock());
        sessionApiClient.login("Gloria", "123222", callback);

        verify(callback).onError();
        verify(callback, never()).onSuccess();
    }

    @Test
    public void returErrorIfEmailIncorrect() throws Exception {
        SessionApiClient.LogInCallback callback = mock(SessionApiClient.LogInCallback.class);
        SessionApiClient sessionApiClient = new SessionApiClient(new FakeExecutor(), new Clock());
        sessionApiClient.login("Glor", "123", callback);

        verify(callback).onError();
        verify(callback, never()).onSuccess();
    }

    @Test
    public void returSuccessIfEmailAndPasswordCorrect() throws Exception {
        SessionApiClient.LogInCallback callback = mock(SessionApiClient.LogInCallback.class);
        SessionApiClient sessionApiClient = new SessionApiClient(new FakeExecutor(), new Clock());
        sessionApiClient.login("Gloria", "123", callback);

        verify(callback).onSuccess();
        verify(callback, never()).onError();
    }


    @Test
    public void returnsOnSuccesDurringTheLogOutIfTheMilliseconsIsEven() throws Exception {
        SessionApiClient.LogOutCallback callback = mock(SessionApiClient.LogOutCallback.class);
        Clock clock = mock(Clock.class);
        when(clock.getCurrentTimeMillis()).thenReturn(2000l);
        SessionApiClient sessionApiClient = new SessionApiClient(new FakeExecutor(), clock);
        sessionApiClient.logout(callback);
        verify(callback).onSuccess();
    }
}
