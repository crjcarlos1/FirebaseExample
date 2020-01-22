package com.cralos.firebase2.RegisterAndLogIn.register.interfaces;

public interface RegisterPresenter {

    /*setDataToInteractor*/
    void registerUser(String name, String email, String password);

    /*setDataToView*/
    void setMessageToView(String message);

    void successRegister();
}
