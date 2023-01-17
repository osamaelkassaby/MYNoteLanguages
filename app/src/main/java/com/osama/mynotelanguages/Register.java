package com.osama.mynotelanguages;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public String URL = "https://osamaelkassaby.com/apps/note/register/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText fname    = findViewById(R.id.firstname);
        EditText lname    = findViewById(R.id.lastname);
        EditText email    = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);


        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register(fname.getText().toString() , lname.getText().toString() , email.getText().toString() , password.getText().toString());
            }
        });

    }
    public void Register(String firstname , String lastname , String email , String password){
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("done")){

                   AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setIcon(R.drawable.sucsess)

                            .setView(R.layout.sucsess);
                   AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    Intent intent = new Intent(Register.this , Login.class);
                    startActivity(intent);
                    finish();
                }else if(response.equals("Email is already Use .")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setTitle("Error")
                            .setIcon(R.drawable.ic_baseline_error_24)
                            .setMessage("Email is already Use .");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setTitle("Error")
                            .setIcon(R.drawable.ic_baseline_error_24)
                            .setMessage("Error in Connection");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register.this , "error",Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> data =  new HashMap<>();
                data.put("register" ,"1");

                data.put("firstname" ,firstname);
                data.put("lastname" ,lastname);
                data.put("email" ,email);
                data.put("password",password);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

}
