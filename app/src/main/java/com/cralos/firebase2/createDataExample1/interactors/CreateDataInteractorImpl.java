package com.cralos.firebase2.createDataExample1.interactors;

import android.util.Log;

import androidx.annotation.NonNull;

import com.cralos.firebase2.createDataExample1.interfaces.CreateDataInteractor;
import com.cralos.firebase2.createDataExample1.interfaces.CreateDataPresenter;
import com.cralos.firebase2.createDataExample1.models.Admin;
import com.cralos.firebase2.createDataExample1.models.Animal;
import com.cralos.firebase2.createDataExample1.utils.FirebaseReferences;
import com.cralos.firebase2.createDataExample1.utils.UtilsFirebase1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateDataInteractorImpl implements CreateDataInteractor {

    private CreateDataPresenter presenter;
    private DatabaseReference reference;

    public CreateDataInteractorImpl(CreateDataPresenter presenter) {
        this.presenter = presenter;
        reference = FirebaseDatabase.getInstance().getReference(); /*referncia al nodo principal de la db*/
    }

    @Override
    public void createNewAdmin() {
        /*
            creamos la siguiente estructura para crear un nuevo admin:
                Usuarios
                    Administradores
                        1skjdgl
                            apellido
                            edad
                            nombre
                        2ñsljgdlkjg
                            apellido
                            edad
                            nombre

         */
        reference.child(FirebaseReferences.USERS).child(FirebaseReferences.ADMINS).push().setValue(UtilsFirebase1.createNewAdmin()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    presenter.setMessageToView("Created new admin");
                } else {
                    presenter.setMessageToView(task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public void createNewClient() {
         /*
            creamos la siguiente estructura para crear un nuevo cliente:
                Usuarios
                    Cliente
                        1sjgñlgkñgkls
                            apellido
                            edad
                            nombre
                        2sdñgñlñskgñ
                            apellido
                            edad
                            nombre

         */
        reference.child(FirebaseReferences.USERS).child(FirebaseReferences.CLIENTS).push().setValue(UtilsFirebase1.createNewClient())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            presenter.setMessageToView("Created new Client");
                        } else {
                            presenter.setMessageToView(task.getException().getMessage());
                        }
                    }
                });
    }

    @Override
    public void deleteClient() {
         /*
            se eliminara el cliente con id -> ' -LyzkR44JssNwOFK-kVa '  de la siguiente estructura en firebase:
                Usuarios
                    Clientes
                        -KJHDFSKJLV4JssNwOFK-kVa
                            apellido
                            edad
                            nombre
                        -DFKLKJJLKJL45644JssNwOFK-kVa
                            apellido
                            edad
                            nombre
                        -LyzkR44JssNwOFK-kVa
                            apellido
                            edad
                            nombre
                        -SLDKJHFLKSAJDÑVLVLKV-kVa
                            apellido
                            edad
                            nombre

         */
        reference.child(FirebaseReferences.USERS).child(FirebaseReferences.CLIENTS).child("-LyzkR44JssNwOFK-kVa").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    presenter.setMessageToView("Se eliminó correctamete");
                } else {
                    presenter.setMessageToView(task.getException().getMessage());
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        presenter.setMessageToView(e.getMessage());
                    }
                });
    }

    @Override
    public void updateAdmin() {
        /*
            se actualizara el admin con id -> ' -LyzkR44JssNwOFK-kVa '  de la siguiente estructura en firebase con los datos : contreras, carlos 18:
                Usuarios
                    Administradores
                        -KJHDFSKJLV4JssNwOFK-kVa
                            apellido
                            edad
                            nombre
                        -DFKLKJJLKJL45644JssNwOFK-kVa
                            apellido
                            edad
                            nombre
                        --LyzkR44JssNwOFK-kVa
                            apellido
                            edad
                            nombre
                        -SLDKJHFLKSAJDÑVLVLKV-kVa
                            apellido
                            edad
                            nombre

         */
        //Admin admin=new Admin("Contreras Ramírez",18,"Juan Carlos");
        Map<String, Object> admin = new HashMap<>();
        admin.put("apellido", "Contreras Ramírez");
        admin.put("edad", 18);
        admin.put("nombre", "Juan Carlos");
        reference.child(FirebaseReferences.USERS).child(FirebaseReferences.ADMINS).child("-LyzkR44JssNwOFK-kVa").updateChildren(admin).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                presenter.setMessageToView(e.getMessage());
            }
        }).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    presenter.setMessageToView("se actualizo correctamente datos del admin");
                } else {
                    presenter.setMessageToView(task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public void getAnimalObjectFromFirebase() {
        reference.child(FirebaseReferences.ANIMAL).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {   /*dataSnapchot es el objeto animal*/
                if (dataSnapshot.exists()) {  /*preguntamos si existe el campo "animal" */
                    String nombre = dataSnapshot.child("nombre").getValue().toString();  /*obtenemos solo el nombre de la mascota*/
                    Log.e("FIREBASE_TAG", ":: " + nombre);
                    Animal animal = dataSnapshot.getValue(Animal.class);    /*obtenemos todo el objeto*/
                    presenter.setMessageToView("datos del animal: " + animal.getNombre() + ", " + animal.getEdad());
                } else {
                    presenter.setMessageToView("no existe el campo animal en firebase, favor de crearlo");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                presenter.setMessageToView(databaseError.getMessage());
            }
        });
    }

    @Override
    public void getAdminList() {
        reference.child(FirebaseReferences.USERS).child(FirebaseReferences.ADMINS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {  /*referencia al campo Administradores (es una lista) */
                if (dataSnapshot != null) {
                    setAdminsToView(dataSnapshot);
                } else {
                    presenter.setMessageToView("no hay datos en la lista");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                presenter.setMessageToView("ERROR: " + databaseError.getMessage());
            }
        });
    }

    private void setAdminsToView(DataSnapshot dataSnapshot) {
        List<Admin> adminList = new ArrayList<>();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            Admin admin = snapshot.getValue(Admin.class);
            adminList.add(admin);
        }
        presenter.setMessageToView("lista con " + adminList.size() + " administradores");
    }

    private void createMessageFirebase(String message) {
        reference.child("texto").setValue(message).addOnCompleteListener(new OnCompleteListener<Void>() {  /*asignamos nodo el hijo "texto" y le agregamos el valor del mensaje*/
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    presenter.setMessageToView("Se registro mensaje en firebase :)");
                } else {
                    presenter.setMessageToView(task.getException().getMessage());
                }
            }
        });
    }

    private void createPerson() {
        Map<String, Object> persona = new HashMap<>();
        persona.put("nombre", "Juan Carlos Cont");
        persona.put("apellido", "Ramirezz");
        persona.put("edad", 20);
        reference.child("Persona").setValue(persona).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    presenter.setMessageToView("Se registro nueva persona en firebase :)");
                } else {
                    presenter.setMessageToView(task.getException().getMessage());
                }
            }
        });
    }


}
