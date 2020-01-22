package com.cralos.firebase2.RegisterAndLogIn.register.presenters;

import com.cralos.firebase2.RegisterAndLogIn.register.interactors.RegisterInteractorImpl;
import com.cralos.firebase2.RegisterAndLogIn.register.interfaces.RegisterInteractor;
import com.cralos.firebase2.RegisterAndLogIn.register.interfaces.RegisterPresenter;
import com.cralos.firebase2.RegisterAndLogIn.register.interfaces.RegisterView;

public class RegisterPresenterImpl implements RegisterPresenter {

    private RegisterView view;
    private RegisterInteractor interactor;

    public RegisterPresenterImpl(RegisterView view) {
        this.view = view;
        interactor = new RegisterInteractorImpl(this);
    }

    @Override
    public void registerUser(String name, String email, String password) {
        if (view != null) {
            view.showLoader();
            interactor.registerUser(name, email, password);
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
    public void successRegister() {
        if (view != null) {
            view.hideLoader();
            view.successRegister();
        }
    }
}
