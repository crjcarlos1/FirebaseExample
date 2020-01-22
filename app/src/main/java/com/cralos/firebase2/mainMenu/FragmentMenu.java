package com.cralos.firebase2.mainMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cralos.firebase2.R;
import com.cralos.firebase2.RegisterAndLogIn.register.view.FragmentRegister;
import com.cralos.firebase2.createDataExample1.view.FragmentBasicOperationsFirebase;

public class FragmentMenu extends Fragment implements View.OnClickListener {
    public static final String TAG = FragmentMenu.class.getSimpleName();

    private Button btnFragmentCreateData, btnRegisterWithFirebase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        initFragmentMenu(view);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCreateDataExample1:
                showFragmentCreateData();
                break;
            case R.id.btnRegisterWithFirebase:
                showFragmentRegister();
                break;
        }
    }

    private void initFragmentMenu(View view) {
        btnFragmentCreateData = view.findViewById(R.id.btnCreateDataExample1);
        btnRegisterWithFirebase = view.findViewById(R.id.btnRegisterWithFirebase);
        btnFragmentCreateData.setOnClickListener(this);
        btnRegisterWithFirebase.setOnClickListener(this);
    }

    private void showFragmentCreateData() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.containerFragments, new FragmentBasicOperationsFirebase(), FragmentBasicOperationsFirebase.TAG).commit();
    }

    private void showFragmentRegister() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentRegister.TAG);
        transaction.replace(R.id.containerFragments, new FragmentRegister(), FragmentRegister.TAG).commit();
    }

}
