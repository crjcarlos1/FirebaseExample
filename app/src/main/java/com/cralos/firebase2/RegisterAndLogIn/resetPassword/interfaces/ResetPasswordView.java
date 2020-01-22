package com.cralos.firebase2.RegisterAndLogIn.resetPassword.interfaces;

public interface ResetPasswordView {
    void showLoader();

    void hideLoader();

    void showMessage(String message);

    void successResetPassword();
}
