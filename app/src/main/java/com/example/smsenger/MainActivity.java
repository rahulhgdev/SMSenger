package com.example.smsenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //variable declaration
    EditText text_phone;
    EditText text_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //variable initialization

        text_phone = (EditText)  findViewById(R.id.text_phone);
        text_msg = (EditText) findViewById(R.id.text_message);
    }

    public void btnSend(View view) {

        //checking permission
        //Context compat

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if(permissionCheck == PackageManager.PERMISSION_GRANTED){

            Message();
        }
        else{
            //Activity Compact
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.SEND_SMS}, 77);
        }
    }

    private void Message() {

        String phoneNumber = text_phone.getText().toString().trim();
        String Message = text_msg.getText().toString().trim();

        if(!text_phone.getText().toString().equals("")  || !text_msg.getText().toString().equals("")) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, Message, null, null);

            Toast.makeText(this, "Message Sent", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"Please Enter Phone Number & Message",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 77:
                if(grantResults.length>=0 && grantResults[77] == PackageManager.PERMISSION_GRANTED){
                    Message();
                }else {
                    Toast.makeText(this,"You don't have permission to access SMS",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}