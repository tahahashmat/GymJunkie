package com.example.mobile_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LegActivity extends AppCompatActivity {

    Button addButton, getWorkouts;
    ImageButton homeButton, mapButton, userButton;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leg_home);

        dbHandler = new DBHandler(LegActivity.this);

        getWorkouts = findViewById(R.id.getWorkouts);
        getWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result;
                String toaster = "";
                result = dbHandler.query("SELECT * FROM legWorkouts ");

                if (result.getCount() == 0) {
                    Toast.makeText(LegActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                while (result.moveToNext()) {
                    System.out.println (result.getString(2));
                    System.out.println (result.getString(3));

                    LinearLayout myRoot = (LinearLayout) findViewById(R.id.linearLay);
                    LinearLayout a = new LinearLayout(LegActivity.this);
                    a.setOrientation(LinearLayout.HORIZONTAL);

                    TextView tv1 = new TextView(LegActivity.this);
                    TextView tv2 = new TextView(LegActivity.this);

                    tv1.setText(result.getString(2));
                    tv2.setText(result.getString(3));

                    tv1.setPadding(20, 10, 20, 10);
                    tv2.setPadding(20, 10, 20, 10);

                    a.addView(tv1);
                    a.addView(tv2);
                    myRoot.addView(a);
                }
            }
        });

        //initiate buttons
        addButton = findViewById(R.id.add);
        homeButton = findViewById(R.id.home);
        mapButton = findViewById(R.id.map);
        userButton = findViewById(R.id.user);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LegActivity.this, MainActivity2.class);
                intent.putExtra("user", getUsername());
                startActivity(intent);
            }
        });
        //update the address given by the user
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LegActivity.this, MapsActivity.class);
                intent.putExtra("user", getUsername());
                startActivity(intent);
            }
        });
        //update the address given by the user
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LegActivity.this, UserActivity.class);
                intent.putExtra("user", getUsername());
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LegActivity.this, LegSubmitActivity.class);
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
