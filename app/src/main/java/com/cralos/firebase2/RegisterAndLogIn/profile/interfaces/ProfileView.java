package com.cralos.firebase2.RegisterAndLogIn.profile.interfaces;

public interface ProfileView {
    void showMessage(String message);

    void successLogOut();

    void showUserInfo(String email, String name);
}
