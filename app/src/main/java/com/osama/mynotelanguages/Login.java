package com.osama.mynotelanguages;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    public String URL = "https://osamaelkassaby.com/apps/note/login/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.login);
        User user = new User(Login.this);

        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this , Register.class);
                startActivity(intent);
            }
        });

        Cursor cursor = user.redData();
        if(cursor.getCount() > 0){
            Intent intent = new Intent(Login.this , MainActivity.class);
            startActivity(intent);
            finish();
        }else {


        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = findViewById(R.id.email);
                EditText password = findViewById(R.id.password);
                login(username.getText().toString() , password.getText().toString());

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this , Register.class);
                startActivity(intent);
                finish();
            }
        });

    }


    public void login(String username , String password){
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        String firstname = object.getString("firstname");
                        String lastname  = object.getString("lastname");
                        String email  = object.getString("email");
                        String password = object.getString("password");
                        String token  = object.getString("token");
                        User user = new User(Login.this);
                        user.adduser(firstname , lastname , email , password , token);
                        Intent intent = new Intent(Login.this , MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }catch (Exception e){
                    Toast.makeText(Login.this,e.toString(),Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this,"Error in connection ."+error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> data =  new HashMap<>();
                data.put("email" ,username);
                data.put("password",password);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }




}