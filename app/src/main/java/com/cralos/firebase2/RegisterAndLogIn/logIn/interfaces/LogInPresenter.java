package com.cralos.firebase2.RegisterAndLogIn.logIn.interfaces;

public interface LogInPresenter {

    /*setDataToInteractor*/
    void validatedata(String email, String password);

    /*setDataToView*/
    void setMessageToView(String message);
    void successLogIn();
}
