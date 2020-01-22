package com.cralos.firebase2.RegisterAndLogIn.resetPassword.interactors;

import androidx.annotation.NonNull;

import com.cralos.firebase2.RegisterAndLogIn.resetPassword.interfaces.ResetPasswordInteractor;
import com.cralos.firebase2.RegisterAndLogIn.resetPassword.interfaces.ResetPasswordPresenter;
import com.cralos.firebase2.RegisterAndLogIn.resetPassword.utils.ResetPasswordUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordInteractorImpl implements ResetPasswordInteractor {
    private ResetPasswordPresenter presenter;
    private FirebaseAuth firebaseAuth;

    public ResetPasswordInteractorImpl(ResetPasswordPresenter presenter) {
        this.presenter = presenter;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void resetPassword(String email) {
        if (ResetPasswordUtils.validateEmail(email)) {
            resetPassWithFirebase(email);
        } else {
            presenter.setMessageToView("Correo no válido");
        }
    }

    private void resetPassWithFirebase(String email) {
        //establecemos idioma del correo que nos llegara para reestablecer el pass
        firebaseAuth.setLanguageCode("es");
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    presenter.setMessageToView("Se envío un correo para reestablecer tu contraseña");
                } else {
                    presenter.setMessageToView(task.getException().getMessage());
                }
            }
        });
    }

}
