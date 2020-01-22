package com.cralos.firebase2.createDataExample1.presenters;

import com.cralos.firebase2.createDataExample1.interactors.CreateDataInteractorImpl;
import com.cralos.firebase2.createDataExample1.interfaces.CreateDataInteractor;
import com.cralos.firebase2.createDataExample1.interfaces.CreateDataPresenter;
import com.cralos.firebase2.createDataExample1.interfaces.CreateDataView;

public class CreateDataPresenterImpl implements CreateDataPresenter {

    private CreateDataView view;
    private CreateDataInteractor interactor;

    public CreateDataPresenterImpl(CreateDataView view) {
        this.view = view;
        interactor = new CreateDataInteractorImpl(this);
    }

    @Override
    public void createNewAdmin() {
        if (view != null) {
            view.showLoader();
            interactor.createNewAdmin();
        }
    }

    @Override
    public void createNewClient() {
        if (view != null) {
            view.showLoader();
            interactor.createNewClient();
        }
    }

    @Override
    public void deleteClient() {
        if (view != null) {
            view.showLoader();
            interactor.deleteClient();
        }
    }

    @Override
    public void updateAdmin() {
        if (view != null) {
            view.showLoader();
            interactor.updateAdmin();
        }
    }

    @Override
    public void getAnimalObjectFromFirebase() {
        if (view != null) {
            view.showLoader();
            interactor.getAnimalObjectFromFirebase();
        }
    }

    @Override
    public void getAdminList() {
        if (view != null) {
            view.showLoader();
            interactor.getAdminList();
        }
    }

    @Override
    public void setMessageToView(String mesagge) {
        if (view != null) {
            view.hideLoader();
            view.showMessage(mesagge);
        }
    }

}
