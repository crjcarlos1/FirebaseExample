package com.cralos.firebase2.RegisterAndLogIn.resetPassword.presenters;

import com.cralos.firebase2.RegisterAndLogIn.resetPassword.interactors.ResetPasswordInteractorImpl;
import com.cralos.firebase2.RegisterAndLogIn.resetPassword.interfaces.ResetPasswordInteractor;
import com.cralos.firebase2.RegisterAndLogIn.resetPassword.interfaces.ResetPasswordPresenter;
import com.cralos.firebase2.RegisterAndLogIn.resetPassword.interfaces.ResetPasswordView;

public class ResetPasswordPresenterImpl implements ResetPasswordPresenter {

    private ResetPasswordView view;
    private ResetPasswordInteractor interactor;

    public ResetPasswordPresenterImpl(ResetPasswordView view) {
        this.view = view;
        interactor = new ResetPasswordInteractorImpl(this);
    }

    @Override
    public void resetPassword(String email) {
        if (view != null) {
            view.showLoader();
            interactor.resetPassword(email);
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
    public void successResetPassword() {
        if (view != null) {
            view.hideLoader();
            view.successResetPassword();
        }
    }
}
