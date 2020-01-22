package com.cralos.firebase2.RegisterAndLogIn.register.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterValidations {

    public static String validateData(String name, String email, String password) {
        String message = "SUCCESS";
        if (name.isEmpty()) {
            message = "Ingresa tu nombre";
            return message;
        }

        if (email.isEmpty()) {
            message = "Ingresa tu correo electronico";
            return message;
        } else {
            if (!validateEmail(email)) {
                message = "Correo electronico no valido";
                return message;
            }
        }

        if (password.isEmpty()) {
            message = "Ingresa contraseña";
            return message;
        }
        if (password.length() <= 6) {
            message = "Ingresa una contraseña con mas de 6 caracteres";
            return message;
        }
        return message;
    }

    private static boolean validateEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
