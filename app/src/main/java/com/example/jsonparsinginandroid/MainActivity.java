package com.example.jsonparsinginandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button button;
    ProgressBar progress;
    TextView name,mobile,email,address;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        progress=findViewById(R.id.progress);
        name=findViewById(R.id.name);
        mobile=findViewById(R.id.mobile);
        email=findViewById(R.id.email);
        address=findViewById(R.id.address);

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


progress.setVisibility(View.VISIBLE);

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://emranrakib.000webhostapp.com/Apps/info.json";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        progress.setVisibility(View.GONE);
                        Log.d("server response",response);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String Name=jsonObject.getString("Name");
                            String Mobile=jsonObject.getString("Mobile");
                            String Email=jsonObject.getString("Email");
                            String Address=jsonObject.getString("Address");

                            name.setText(Name);
                            mobile.setText(Mobile);
                            email.setText(Email);
                            address.setText(Address);


                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                button.setText("Volley Error!");
                progress.setVisibility(View.GONE);
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);









    }
});


    }
}