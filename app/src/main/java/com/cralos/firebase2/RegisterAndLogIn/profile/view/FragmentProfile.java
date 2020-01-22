package com.cralos.firebase2.RegisterAndLogIn.profile.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cralos.firebase2.R;
import com.cralos.firebase2.RegisterAndLogIn.profile.interfaces.ProfilePresenter;
import com.cralos.firebase2.RegisterAndLogIn.profile.interfaces.ProfileView;
import com.cralos.firebase2.RegisterAndLogIn.profile.presenters.ProfilePresenterImpl;

public class FragmentProfile extends Fragment implements View.OnClickListener, ProfileView {
    public static final String TAG = FragmentProfile.class.getSimpleName();

    private Button btnLogOut;
    private TextView txvUserData;
    private ProfilePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initFragmentProfile(view);
        return view;
    }

    private void initFragmentProfile(View view) {
        btnLogOut = view.findViewById(R.id.btnLogOut);
        txvUserData = view.findViewById(R.id.txvUserData);
        btnLogOut.setOnClickListener(this);
        presenter = new ProfilePresenterImpl(this);
        presenter.getUserInfo();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogOut:
                presenter.logOut();
                break;
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successLogOut() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void showUserInfo(String email, String name) {
        txvUserData.setText("" + name + "\n" + email);
    }
}
