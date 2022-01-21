package com.example.figures;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class Login extends AppCompatActivity {

    Button btnMap;
    private static final String TAG = "Main Activity";
    private static final int ERROR_DIALOG_REQUEST = 2001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (isServicesOK()) {
            init();
        }
    }

    //button to access the map activity
    private  void init(){
        Button  Map;
        Map = findViewById(R.id.btnMap);
        Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, MapsActivity.class);
                startActivity(i);

            }
        });
    }
    //boolean to check if they have the correct version of google maps
    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Login.this);
        if (available == ConnectionResult.SUCCESS){
            //everything is ok
            Log.d(TAG, "isServicesOK: google pay services is working");
            return true;

        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but you can fix it as the user
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Login.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();

        }else{
            Toast.makeText(this, "map request failed", Toast.LENGTH_SHORT).show();
        }
        return false;

    }
}

