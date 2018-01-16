package com.example.abhishek.mymusic;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button register, log_in;
    EditText First_Name, Last_Name, Email, Password ;
    String F_Name_Holder, L_Name_Holder, EmailHolder, PasswordHolder;
    String HttpURL = "https://androidjsonblog.000webhostapp.com/User/UserRegistration.php";
    String finalResult;
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
     HttpParser httpParse = new HttpParser();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Assigning Id's
        First_Name = (EditText)findViewById(R.id.editTextF_Name);
        Last_Name = (EditText)findViewById(R.id.editTextL_Name);
        Email = (EditText)findViewById(R.id.editTextEmail);
        Password = (EditText)findViewById(R.id.editTextPassword);

        register = (Button)findViewById(R.id.Submit);
        log_in = (Button)findViewById(R.id.Login);

        //Adding Click Listener on button.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               CheckEditTextIsEmptyOrNot();

                if (CheckEditText)
                {

                }
                else
                {
                   Toast.makeText(MainActivity.this,"Please fill all form fields.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UserLoginActivity.class);
                startActivity(intent);
            }
        });
    }
        public void CheckEditTextIsEmptyOrNot(){
        F_Name_Holder = First_Name.getText().toString();
        L_Name_Holder = Last_Name.getText().toString();
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

         if(TextUtils.isEmpty(F_Name_Holder)||TextUtils.isEmpty(L_Name_Holder)||TextUtils.isEmpty(EmailHolder)||TextUtils.isEmpty(PasswordHolder))
         {
             CheckEditText=false;
         }
         else
         {
             CheckEditText=true;
         }

    }
    public void UserRegisterationFunction(final String F_Name, final String L_Name, final String email, final String password){
         class UserRegisterationFunctionClass extends AsyncTask<String,Void,String>{

             @Override
             protected void onPreExecute() {
                 super.onPreExecute();
                 progressDialog=progressDialog.show(MainActivity.this,"Loading Data",null,true,true);

             }

             @Override
             protected void onPostExecute(String HttpResponseMsg) {
                 super.onPostExecute(HttpResponseMsg);
                 progressDialog.dismiss();
                 Toast.makeText(MainActivity.this,HttpResponseMsg.toString(),Toast.LENGTH_LONG).show();
             }

             @Override
             protected String doInBackground(String... params) {
                 hashMap.put("f_name",params[0]);

                 hashMap.put("L_name",params[1]);

                 hashMap.put("email",params[2]);

                 hashMap.put("password",params[3]);

                 try {
                     finalResult = httpParse.postRequest(hashMap, HttpURL);
                 } catch (MalformedURLException e) {
                     e.printStackTrace();
                 }

                 return finalResult;
                // return null;
             }
         }
}

}
