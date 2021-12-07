package com.example.mobile_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements registerDialog.DialogListener, loginDialog.DialogListener{
    Button loginButton, registerButton;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        dbHandler = new DBHandler(MainActivity.this);


        registerButton = findViewById(R.id.registerButton);
        //update the address given by the user
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerDialog();
            }
        });
        loginButton = findViewById(R.id.loginButton);
        //update the address given by the user
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDialog();
            }
        });

    }
    public void registerDialog(){
        //calls Dialog pop up
        registerDialog Dialog = new registerDialog();
        Dialog.show(getSupportFragmentManager(), "register dialog");
    }
    public void loginDialog(){
        //calls Dialog pop up
        loginDialog newDialog = new loginDialog();
        newDialog.show(getSupportFragmentManager(), "login dialog");
    }
    @Override
    public void register(String username, String password) {
        //calls db to update
        String response = dbHandler.register(username, password);
        if (response.equals("success")){
            Toast.makeText(MainActivity.this, "New user has been created", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void login(String username, String password) {
        //calls db to update
        String response = dbHandler.login(username, password);
        if (response.equals("failed")){
            Toast.makeText(MainActivity.this, "Username and Password don't match", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, "Welcome " + response, Toast.LENGTH_SHORT).show();
            openActivity2(response);
        }

    }

    private void openActivity2(String username) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("user", username);
        startActivity(intent);
    }
}