package com.example.abhishek.mymusic;

import android.app.ProgressDialog;
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

public class UserLoginActivity extends AppCompatActivity {
    EditText Email, Password;
    Button LogIn ;
    String PasswordHolder, EmailHolder;
    String finalResult ;
    String HttpURL = "";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
   public HashMap<String,String> hashMap = new HashMap<>();
    HttpParser httpParser=new HttpParser();
    public static final String UserEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        LogIn=findViewById(R.id.Login);
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextIsEmptyOrNot();
                if(CheckEditText)
                {
                  userLoginFunction(EmailHolder,PasswordHolder);
                }
                else
                {
                   Toast.makeText(UserLoginActivity.this,"Please fill all form fields.",Toast.LENGTH_SHORT).show();
                }
            }
            public void CheckEditTextIsEmptyOrNot()
            {
                EmailHolder=Email.getText().toString();
                PasswordHolder=Password.getText().toString();

                if(TextUtils.isEmpty((EmailHolder))||TextUtils.isEmpty(PasswordHolder))
                {
                    CheckEditText=false;

                }
                else
                {
                    CheckEditText=true;
                }

            }

            public void userLoginFunction(final String email,final String password )
            {
                class UserLoginClass extends AsyncTask<String,Void,String>{

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        progressDialog=progressDialog.show(UserLoginActivity.this,"Loading Data",null,true);
                    }

                    @Override
                    protected void onPostExecute(String httpresponsemsg) {
                        super.onPostExecute(httpresponsemsg);
                        progressDialog.dismiss();
                        if(httpresponsemsg.equalsIgnoreCase("Data Matched"))
                        {
                            finish();
                            Intent intent=new Intent(UserLoginActivity.this,DashboardActivity.class);
                            intent.putExtra(UserEmail,email);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(UserLoginActivity.this,httpresponsemsg.toString(),Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public String doInBackground(String... params) {
                        hashMap.put("email",params[0]);

                        hashMap.put("password",params[1]);
                        try {
                            finalResult=httpParser.postRequest(hashMap,HttpURL);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                        return finalResult;
                    }

                }
                UserLoginClass userLoginClass=new UserLoginClass();
                userLoginClass.execute(email,password);
            }
        });

    }
}
