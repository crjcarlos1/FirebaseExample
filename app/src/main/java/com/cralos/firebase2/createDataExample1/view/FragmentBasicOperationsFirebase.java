package com.cralos.firebase2.createDataExample1.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cralos.firebase2.R;
import com.cralos.firebase2.createDataExample1.interfaces.CreateDataPresenter;
import com.cralos.firebase2.createDataExample1.interfaces.CreateDataView;
import com.cralos.firebase2.createDataExample1.presenters.CreateDataPresenterImpl;

public class FragmentBasicOperationsFirebase extends Fragment implements View.OnClickListener, CreateDataView {
    public static final String TAG = FragmentBasicOperationsFirebase.class.getSimpleName();

    private Button btnCreateData, btnCreateClient, btnDeleteData;
    private Button btnUpdateData, btnGetAnimalObject, btnGetAdminList;
    private ProgressBar progressBar;

    private CreateDataPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basics_operations, container, false);
        initFragmentCreateData(view);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCreateData:
                presenter.createNewAdmin();
                break;
            case R.id.btnCreateClient:
                presenter.createNewClient();
                break;
            case R.id.btnDeleteData:
                presenter.deleteClient();
                break;
            case R.id.btnUpdateData:
                presenter.updateAdmin();
                break;
            case R.id.btnGetAnimalObject:
                presenter.getAnimalObjectFromFirebase();
                break;
            case R.id.btnGetAdminList:
                presenter.getAdminList();
                break;
        }
    }

    private void initFragmentCreateData(View view) {
        btnCreateData = view.findViewById(R.id.btnCreateData);
        btnCreateClient = view.findViewById(R.id.btnCreateClient);
        btnDeleteData = view.findViewById(R.id.btnDeleteData);
        btnUpdateData = view.findViewById(R.id.btnUpdateData);
        btnGetAnimalObject = view.findViewById(R.id.btnGetAnimalObject);
        btnGetAdminList = view.findViewById(R.id.btnGetAdminList);
        progressBar = view.findViewById(R.id.progressBar);
        btnCreateData.setOnClickListener(this);
        btnCreateClient.setOnClickListener(this);
        btnDeleteData.setOnClickListener(this);
        btnUpdateData.setOnClickListener(this);
        btnGetAnimalObject.setOnClickListener(this);
        btnGetAdminList.setOnClickListener(this);
        presenter = new CreateDataPresenterImpl(this);
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
    public void successCreatedData() {
        showMessage("SUCCESS CREATED DATA");
    }
}
