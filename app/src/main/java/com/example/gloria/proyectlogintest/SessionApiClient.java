package com.example.gloria.proyectlogintest;

/**
 * Created by gloria on 21/4/17.
 */

public class SessionApiClient {

    private final Executor executor;  // Las tenemos que pasar en el constructor para poder usarlas en los test
    private final Clock clock;

    public SessionApiClient(Executor executor, Clock clock) {
        this.executor = executor;
        this.clock = clock;
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
              //  if (System.currentTimeMillis()%2 == 0) Tenemos que crear una clase Clock que nos devuelva el valor en milisegundos y no
                //usar directamente System para poder tener en los test cubierto tambien este metodo. De eotra forma no podríamos controlar el
                // tiempo
                if (clock.getCurrentTimeMillis()%2 == 0){
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
