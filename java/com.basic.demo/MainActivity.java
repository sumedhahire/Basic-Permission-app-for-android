package com.example.demo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int PHONE_REQ=100;
    int SMS_REQ=101;
    Button btnnext,btnphonereq,btnsmsreq;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==PHONE_REQ){
            if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"phone permission granted",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"phone permission denied",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            if(requestCode==SMS_REQ){
                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(),"SMS permission granted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"SMS permission denied",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void checkPermission(String permission, int requestCode) {
        int granted=ContextCompat.checkSelfPermission(MainActivity.this,permission);
        if(granted==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{ permission},requestCode);
        }
        else{
            Toast.makeText(getApplicationContext(),"permission already granted",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnphonereq=findViewById(R.id.btnPhoneReq);
        btnsmsreq=findViewById(R.id.btnSmsReq);

        btnnext=findViewById(R.id.btnNext);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(i);
            }
        });

        btnphonereq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        checkPermission(Manifest.permission.CALL_PHONE,PHONE_REQ);

            }
        });

        btnsmsreq.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission(Manifest.permission.SEND_SMS,SMS_REQ);
            }
        }));

    }
}
