package com.example.thepranami.taxiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginButton, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init(){
        loginButton=(Button)findViewById(R.id.loginButton);
        registerButton=(Button)findViewById(R.id.registerButton);
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.loginButton){
            Intent loginIntent=new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        }else if (view.getId()==R.id.registerButton){
            Intent registerIntent=new Intent(HomeActivity.this, RegistrationActivity.class);
            startActivity(registerIntent);
        }
    }
}
