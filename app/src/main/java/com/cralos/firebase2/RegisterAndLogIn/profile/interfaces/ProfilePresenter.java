package com.cralos.firebase2.RegisterAndLogIn.profile.interfaces;

public interface ProfilePresenter {

    /*setDataToInteractor*/
    void logOut();

    void getUserInfo();

    /*setDataToView*/
    void successLogOut();

    void setMessageToView(String message);

    void setUserInfoToView(String email, String name);
}
