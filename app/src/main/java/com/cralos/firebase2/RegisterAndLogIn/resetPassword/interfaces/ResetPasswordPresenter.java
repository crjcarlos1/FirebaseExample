package com.cralos.firebase2.RegisterAndLogIn.resetPassword.interfaces;

public interface ResetPasswordPresenter {
    /*setDataToInteractor*/
    void resetPassword(String email);

    /*setDataToView*/
    void setMessageToView(String message);

    void successResetPassword();
}
