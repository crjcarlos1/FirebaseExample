package com.cralos.firebase2.RegisterAndLogIn.register.interfaces;

public interface RegisterView {
    void showLoader();

    void hideLoader();

    void showMessage(String message);

    void successRegister();
}
