package com.cralos.firebase2.createDataExample1.interfaces;

public interface CreateDataPresenter {

    /*setDataToInteractor*/
    void createNewAdmin();
    void createNewClient();
    void deleteClient();
    void updateAdmin();
    void getAnimalObjectFromFirebase();
    void getAdminList();

    /*setDataToView*/
    void setMessageToView(String mesagge);

}
