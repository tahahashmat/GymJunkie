package com.example.mobile_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LegSubmitActivity extends AppCompatActivity {
    ImageButton homeButton, mapButton, userButton;
    Button addExerciseButton;
    EditText exerciseName, exerciseDescription;
    DBHandler dbHandler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leg_submit);

        dbHandler = new DBHandler(LegSubmitActivity.this);

        homeButton = findViewById(R.id.home);
        mapButton = findViewById(R.id.map);
        userButton = findViewById(R.id.user);


        exerciseName = findViewById(R.id.exerciseName);
        exerciseDescription = findViewById(R.id.exerciseDescription);

        addExerciseButton = findViewById(R.id.addExercise);
        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exerciseN = exerciseName.getText().toString();
                String exerciseD = exerciseDescription.getText().toString();
                int answer;

                if (exerciseN.isEmpty() || exerciseD.isEmpty()) {
                    Toast.makeText(LegSubmitActivity.this, "Please enter the required fields to add workout!", Toast.LENGTH_SHORT).show();
                    return;
                }
                answer = dbHandler.addLegWorkout(getUsername(),exerciseN, exerciseD);

                if (answer == 1){
                    Toast.makeText(LegSubmitActivity.this, "Workout Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LegSubmitActivity.this, LegActivity.class);
                    intent.putExtra("user", getUsername());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LegSubmitActivity.this, "Failed to add workout", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LegSubmitActivity.this, ArmActivity.class);
                    intent.putExtra("user", getUsername());
                    startActivity(intent);
                }
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LegSubmitActivity.this, MainActivity2.class);
                intent.putExtra("user", getUsername());
                startActivity(intent);
            }
        });
        //update the address given by the user
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LegSubmitActivity.this, MapsActivity.class);
                intent.putExtra("user", getUsername());
                startActivity(intent);
            }
        });
        //update the address given by the user
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LegSubmitActivity.this, UserActivity.class);
                intent.putExtra("user", getUsername());
                startActivity(intent);
            }
        });
    }
    public String getUsername() {
        Intent intent = getIntent();
        String username = intent.getExtras().getString("user");
        return username;
    }
}