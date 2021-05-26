package com.example.easycheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    private TextView user_name;
    DocumentReference ref;
//    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mAuth = FirebaseAuth.getInstance();
//        String uuid = mAuth.getCurrentUser().getUid();
        user_name = findViewById(R.id.user_name);
        db = FirebaseFirestore.getInstance();
        String userpath = "users"+"/"+WelcomeScreen.uuid;
//        String username = db.collection(userpath).get("firstname").toString();
//        user_name.setText(username);
        ref = db.collection("users").document(WelcomeScreen.uuid);
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot doc = task.getResult();
                    if(doc.exists()){
                        user_name.setText(doc.getData().get("firstname").toString());
                    }
                }
            }
        });

    }
}