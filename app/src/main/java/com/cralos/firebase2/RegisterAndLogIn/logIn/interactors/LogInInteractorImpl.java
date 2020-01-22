package com.cralos.firebase2.RegisterAndLogIn.logIn.interactors;

import androidx.annotation.NonNull;

import com.cralos.firebase2.RegisterAndLogIn.logIn.interfaces.LogInInteractor;
import com.cralos.firebase2.RegisterAndLogIn.logIn.interfaces.LogInPresenter;
import com.cralos.firebase2.RegisterAndLogIn.logIn.utils.LogInValidations;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInInteractorImpl implements LogInInteractor {
    private LogInPresenter presenter;
    private FirebaseAuth firebaseAuth;

    public LogInInteractorImpl(LogInPresenter presenter) {
        this.presenter = presenter;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void validatedata(String email, String password) {
        String validation = LogInValidations.validateData(email, password);
        if (validation.equals("SUCCESS")) {
            logInWithFirebase(email, password);
        } else {
            presenter.setMessageToView(validation);
        }
    }

    private void logInWithFirebase(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    presenter.successLogIn();
                } else {
                    presenter.setMessageToView(task.getException().getMessage());
                }
            }
        });
    }

}
