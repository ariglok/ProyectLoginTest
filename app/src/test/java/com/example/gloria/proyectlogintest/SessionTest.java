package com.example.gloria.proyectlogintest;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by gloria on 21/4/17.
 */

public class SessionTest {
    @Test
    public void returnErrorIfTjeUserIfEmpty() throws Exception {
        SessionApiClient.LogInCallback callback = mock(SessionApiClient.LogInCallback.class);
        SessionApiClient sessionApiClient = new SessionApiClient(new FakeExecutor());
        sessionApiClient.login("", "123", callback);

        verify(callback).onError();
        verify(callback,never()).onSuccess();

    }
}
