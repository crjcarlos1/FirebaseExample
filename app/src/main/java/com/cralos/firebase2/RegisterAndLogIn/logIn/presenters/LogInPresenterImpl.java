package com.cralos.firebase2.RegisterAndLogIn.logIn.presenters;

import com.cralos.firebase2.RegisterAndLogIn.logIn.interactors.LogInInteractorImpl;
import com.cralos.firebase2.RegisterAndLogIn.logIn.interfaces.LogInInteractor;
import com.cralos.firebase2.RegisterAndLogIn.logIn.interfaces.LogInPresenter;
import com.cralos.firebase2.RegisterAndLogIn.logIn.interfaces.LogInView;

public class LogInPresenterImpl implements LogInPresenter {

    private LogInView view;
    private LogInInteractor inInteractor;

    public LogInPresenterImpl(LogInView view) {
        this.view = view;
        inInteractor = new LogInInteractorImpl(this);
    }

    @Override
    public void validatedata(String email, String password) {
        if (view != null) {
            view.showLoader();
            inInteractor.validatedata(email, password);
        }
    }

    @Override
    public void setMessageToView(String message) {
        if (view != null) {
            view.hideLoader();
            view.showMessage(message);
        }
    }

    @Override
    public void successLogIn() {
        if (view != null) {
            view.hideLoader();
            view.successLogIn();
        }
    }

}
