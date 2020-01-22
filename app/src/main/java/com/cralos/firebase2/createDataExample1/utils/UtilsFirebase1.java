package com.cralos.firebase2.createDataExample1.utils;

import com.cralos.firebase2.createDataExample1.models.Admin;
import com.cralos.firebase2.createDataExample1.models.Client;

public class UtilsFirebase1 {

    public static Admin createNewAdmin() {
        int random = (int) (Math.random() * 5000 + 1);
        String apellido = "Apellido " + random + "";
        int edad = random;
        String nombre = "Nombre " + random + "";
        Admin admin = new Admin(apellido, edad, nombre);
        return admin;
    }

    public static Client createNewClient() {
        int random = (int) (Math.random() * 5000 + 1);
        String apellido = "Apellido " + random + "";
        int edad = random;
        String nombre = "Nombre " + random + "";
        Client client = new Client(apellido, edad, nombre);
        return client;
    }

}
