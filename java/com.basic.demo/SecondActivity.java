package com.example.demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    Button btncall,btnsms;
    EditText txtcall,txtphone,txtsms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btncall=findViewById(R.id.btnCall);
        btnsms=findViewById(R.id.btnSms);

        txtcall=findViewById(R.id.txtCall);
        txtphone=findViewById(R.id.txtPhone);
        txtsms=findViewById(R.id.txtSms);

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent i=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+txtcall.getText().toString()));
                    startActivity(i);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Some error occured",Toast.LENGTH_LONG).show();

                }
            }
        });

        btnsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                Uri uri=Uri.parse("smsto:"+txtphone.getText().toString());
                i = new Intent(Intent.ACTION_SENDTO,uri);
                i.putExtra("sms_body",txtsms.getText().toString());
                startActivity(i);
            }
        });

    }
}
