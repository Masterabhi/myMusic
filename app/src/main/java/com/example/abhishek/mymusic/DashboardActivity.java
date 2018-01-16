package com.example.abhishek.mymusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    Button LogOut;
    TextView EmailShow;
    String EmailHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        LogOut=findViewById(R.id.button);
        EmailShow=findViewById(R.id.EmailShow);

        Intent intent=getIntent();
        EmailHolder=intent.getStringExtra(UserLoginActivity.UserEmail);
        EmailShow.setText(EmailHolder);

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent1=new Intent(DashboardActivity.this,UserLoginActivity.class);
                startActivity(intent1);
                Toast.makeText(DashboardActivity.this,"Logout Succefully",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
