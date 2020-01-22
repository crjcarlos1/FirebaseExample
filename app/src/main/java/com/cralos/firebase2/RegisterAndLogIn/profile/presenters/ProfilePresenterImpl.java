package com.cralos.firebase2.RegisterAndLogIn.profile.presenters;

import com.cralos.firebase2.RegisterAndLogIn.profile.interactors.ProfileInteractorImpl;
import com.cralos.firebase2.RegisterAndLogIn.profile.interfaces.ProfileInteractor;
import com.cralos.firebase2.RegisterAndLogIn.profile.interfaces.ProfilePresenter;
import com.cralos.firebase2.RegisterAndLogIn.profile.interfaces.ProfileView;

public class ProfilePresenterImpl implements ProfilePresenter {

    private ProfileView view;
    private ProfileInteractor interactor;

    public ProfilePresenterImpl(ProfileView view) {
        this.view = view;
        interactor = new ProfileInteractorImpl(this);
    }

    @Override
    public void logOut() {
        if (view != null) {
            interactor.logOut();
        }
    }

    @Override
    public void getUserInfo() {
        if (view != null) {
            interactor.getUserInfo();
        }
    }

    @Override
    public void successLogOut() {
        if (view != null) {
            view.successLogOut();
        }
    }

    @Override
    public void setMessageToView(String message) {
        if (view != null) {
            view.showMessage(message);
        }
    }

    @Override
    public void setUserInfoToView(String email, String name) {
        if (view != null) {
            view.showUserInfo(email, name);
        }
    }

}
