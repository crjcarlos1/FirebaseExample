package com.cralos.firebase2.RegisterAndLogIn.register.view;

import android.os.Bundle;
import android.util.Log;
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
import com.cralos.firebase2.RegisterAndLogIn.logIn.view.FragmentLogIn;
import com.cralos.firebase2.RegisterAndLogIn.profile.view.FragmentProfile;
import com.cralos.firebase2.RegisterAndLogIn.register.interfaces.RegisterPresenter;
import com.cralos.firebase2.RegisterAndLogIn.register.interfaces.RegisterView;
import com.cralos.firebase2.RegisterAndLogIn.register.presenters.RegisterPresenterImpl;
import com.google.firebase.auth.FirebaseAuth;

public class FragmentRegister extends Fragment implements View.OnClickListener, RegisterView {
    public static final String TAG = FragmentRegister.class.getSimpleName();

    private EditText edtName, edtEmail, edtPassword;
    private Button btnRegister;
    private TextView txvLogIn;
    private ProgressBar progressBar;

    private RegisterPresenter presenter;
    private FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initFragmentRegister(view);
        return view;
    }

    /**
     * Para saber si el usuario tiene la sesion iniciada, que se autentique de forma automatica
     */
    @Override
    public void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null)// si ya inicio sesion el usuario
        {
            Log.e("FIREBASE_TAG", "USUARIO CON SESION INICIADA: " + firebaseAuth.getCurrentUser().getEmail());
            successRegister();
        } else {
            Log.e("FIREBASE_TAG", "USUARIO noooooo tiene SESION INICIADA: ");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                presenter.registerUser(edtName.getText().toString(), edtEmail.getText().toString(), edtPassword.getText().toString());
                break;
            case R.id.txvLogIn:
                showFragmentLogIn();
                break;
        }
    }

    private void initFragmentRegister(View view) {
        edtName = view.findViewById(R.id.edtName);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
        btnRegister = view.findViewById(R.id.btnRegister);
        txvLogIn = view.findViewById(R.id.txvLogIn);
        progressBar = view.findViewById(R.id.progressBar);
        btnRegister.setOnClickListener(this);
        txvLogIn.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        presenter = new RegisterPresenterImpl(this);
    }

    private void showFragmentLogIn() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentLogIn.TAG);
        transaction.replace(R.id.containerFragments, new FragmentLogIn(), FragmentLogIn.TAG).commit();
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
    public void successRegister() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentProfile.TAG);
        transaction.replace(R.id.containerFragments, new FragmentProfile(), FragmentProfile.TAG).commit();
    }
}
