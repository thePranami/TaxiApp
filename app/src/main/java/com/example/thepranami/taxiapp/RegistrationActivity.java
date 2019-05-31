package com.example.thepranami.taxiapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
    private EditText email, password, conPassword;
    private Button registerButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=email.getText().toString().trim();
                String Password=password.getText().toString().trim();
                String ConPass=conPassword.getText().toString();
                if (Password.equals(ConPass)){

                }
                firebaseAuth.createUserWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                sendEmailVerification(firebaseUser);
                                Toast.makeText(RegistrationActivity.this, "Message" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                if (!task.isSuccessful()){
                                    Toast.makeText(RegistrationActivity.this, "Authentication failed!", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    startActivity(new Intent(RegistrationActivity.this, HomeActivity.class));
                                    finish();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("aaaaa", e.toString());
                    }
                });
            }
        });
    }

    private void init(){
        email=(EditText)findViewById(R.id.email_id);
        password=(EditText)findViewById(R.id.password_id);
        conPassword=(EditText)findViewById(R.id.conPassword_id);
        registerButton=(Button)findViewById(R.id.registerButton_id);
        firebaseAuth=FirebaseAuth.getInstance();

    }
    private void sendEmailVerification(FirebaseUser firebaseUser){
        assert firebaseUser != null;
        firebaseUser.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            FirebaseAuth.getInstance().signOut();
                            Toast.makeText(RegistrationActivity.this, "Check Your Email To Verify Your Account!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrationActivity.this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                    |Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    |Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                        }else {
                            overridePendingTransition(0,0);
                            finish();
                            overridePendingTransition(0,0);
                            startActivity(getIntent());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistrationActivity.this, "onFailure..."+e, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
