package com.cralos.firebase2.RegisterAndLogIn.logIn.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogInValidations {
    public static String validateData(String email, String password) {
        String message = "SUCCESS";

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
