package com.cralos.firebase2.RegisterAndLogIn.logIn.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cralos.firebase2.R;
import com.cralos.firebase2.RegisterAndLogIn.logIn.interfaces.LogInPresenter;
import com.cralos.firebase2.RegisterAndLogIn.logIn.interfaces.LogInView;
import com.cralos.firebase2.RegisterAndLogIn.logIn.presenters.LogInPresenterImpl;
import com.cralos.firebase2.RegisterAndLogIn.profile.view.FragmentProfile;
import com.cralos.firebase2.RegisterAndLogIn.resetPassword.view.FragmentResetPassword;

public class FragmentLogIn extends Fragment implements View.OnClickListener, LogInView {
    public static final String TAG = FragmentLogIn.class.getSimpleName();

    private TextView txvResetPassword;
    private EditText edtEmail, edtPassword;
    private Button btnLogIn;
    private ProgressBar progressBar;

    private LogInPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initFragmentLogIn(view);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogIn:
                presenter.validatedata(edtEmail.getText().toString(), edtPassword.getText().toString());
                break;
            case R.id.txvResetPassword:
                showFragmentResetPassword();
                break;
        }
    }

    private void initFragmentLogIn(View view) {
        txvResetPassword = view.findViewById(R.id.txvResetPassword);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
        btnLogIn = view.findViewById(R.id.btnLogIn);
        progressBar = view.findViewById(R.id.progressBar);
        btnLogIn.setOnClickListener(this);
        txvResetPassword.setOnClickListener(this);
        presenter = new LogInPresenterImpl(this);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successLogIn() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentProfile.TAG);
        transaction.replace(R.id.containerFragments, new FragmentProfile(), FragmentProfile.TAG).commit();
    }

    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
    }

    private void showFragmentResetPassword() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentResetPassword.TAG);
        transaction.replace(R.id.containerFragments, new FragmentResetPassword(), FragmentResetPassword.TAG).commit();
    }

}
