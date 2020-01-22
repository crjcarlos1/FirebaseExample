package com.cralos.firebase2.RegisterAndLogIn.resetPassword.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cralos.firebase2.R;
import com.cralos.firebase2.RegisterAndLogIn.resetPassword.interfaces.ResetPasswordPresenter;
import com.cralos.firebase2.RegisterAndLogIn.resetPassword.interfaces.ResetPasswordView;
import com.cralos.firebase2.RegisterAndLogIn.resetPassword.presenters.ResetPasswordPresenterImpl;

public class FragmentResetPassword extends Fragment implements View.OnClickListener, ResetPasswordView {
    public static final String TAG = FragmentResetPassword.class.getSimpleName();

    private EditText edtEmail;
    private ProgressBar progressBar;
    private Button btnResetPassword;

    private ResetPasswordPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        initFragmentResetPassword(view);
        return view;
    }

    private void initFragmentResetPassword(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        edtEmail = view.findViewById(R.id.edtEmail);
        btnResetPassword = view.findViewById(R.id.btnResetPassword);
        btnResetPassword.setOnClickListener(this);
        presenter = new ResetPasswordPresenterImpl(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnResetPassword:
                presenter.resetPassword(edtEmail.getText().toString());
                break;
        }
    }

    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successResetPassword() {

    }

}
