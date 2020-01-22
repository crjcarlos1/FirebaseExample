package com.cralos.firebase2.RegisterAndLogIn.profile.interactors;

import androidx.annotation.NonNull;

import com.cralos.firebase2.RegisterAndLogIn.profile.interfaces.ProfileInteractor;
import com.cralos.firebase2.RegisterAndLogIn.profile.interfaces.ProfilePresenter;
import com.cralos.firebase2.createDataExample1.utils.FirebaseReferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileInteractorImpl implements ProfileInteractor {
    private ProfilePresenter presenter;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;

    public ProfileInteractorImpl(ProfilePresenter presenter) {
        this.presenter = presenter;
        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void logOut() {
        firebaseAuth.signOut();
        presenter.successLogOut();
    }

    @Override
    public void getUserInfo() {
        String id = firebaseAuth.getCurrentUser().getUid();
        reference.child(FirebaseReferences.USERS_REGISTERED).child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("userName").getValue().toString();
                String email = dataSnapshot.child("userEmail").getValue().toString();
                presenter.setUserInfoToView(email, name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                presenter.setMessageToView(databaseError.getMessage());
            }
        });
    }

}
