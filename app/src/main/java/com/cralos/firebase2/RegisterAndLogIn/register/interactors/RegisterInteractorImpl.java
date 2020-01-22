package com.cralos.firebase2.RegisterAndLogIn.register.interactors;

import androidx.annotation.NonNull;

import com.cralos.firebase2.RegisterAndLogIn.register.interfaces.RegisterInteractor;
import com.cralos.firebase2.RegisterAndLogIn.register.interfaces.RegisterPresenter;
import com.cralos.firebase2.RegisterAndLogIn.register.utils.RegisterConstants;
import com.cralos.firebase2.RegisterAndLogIn.register.utils.RegisterValidations;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterInteractorImpl implements RegisterInteractor {
    private RegisterPresenter presenter;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;

    public RegisterInteractorImpl(RegisterPresenter presenter) {
        this.presenter = presenter;
        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void registerUser(String name, String email, String password) {
        String validation = RegisterValidations.validateData(name, email, password);
        if (validation.equals("SUCCESS")) {
            registerWithFirebase(name, email, password);
        } else {
            presenter.setMessageToView(validation);
        }
    }

    private void registerWithFirebase(final String name, final String email, final String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    successRegister(task, name, email, password);
                } else {
                    presenter.setMessageToView(task.getException().getMessage());
                }
            }
        });
    }

    private void successRegister(Task<AuthResult> task, String name, String email, String password) {
        Map<String, Object> user = new HashMap<>();
        user.put("userName", name);
        user.put("userEmail", email);
        user.put("userPassword", password);
        String userId = firebaseAuth.getCurrentUser().getUid();
        reference.child(RegisterConstants.USERS_REGISTERED).child(userId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                processUserRegistered(task);
            }
        });
    }

    private void processUserRegistered(Task<Void> task) {
        if (task.isSuccessful()) {
            presenter.successRegister();
        } else {
            presenter.setMessageToView(task.getException().getMessage());
        }
    }

}
