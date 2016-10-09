package com.examples.rehan.excluzo.Activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.examples.rehan.excluzo.Models.User;
import com.examples.rehan.excluzo.Parsers.UserParser;
import com.examples.rehan.excluzo.Preferences.LoginPreferences;
import com.examples.rehan.excluzo.R;
import com.examples.rehan.excluzo.ServerUtils.ServerRequest;
import com.examples.rehan.excluzo.Utils.DialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameET,emailET,passwordET,ageET,mobilenumberET,GenderET;
    Button signupBT;
    LoginPreferences loginPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginPreferences=new LoginPreferences(RegisterActivity.this);
        emailET=(EditText)findViewById(R.id.email);
        usernameET=(EditText)findViewById(R.id.username);
        passwordET=(EditText)findViewById(R.id.password);
        ageET=(EditText)findViewById(R.id.age);
        mobilenumberET=(EditText)findViewById(R.id.mobile_number);
        GenderET=(EditText)findViewById(R.id.gender);
        signupBT=(Button)findViewById(R.id.signup);

        signupBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();
                String ageString=ageET.getText().toString();
                int age=new Integer(ageString).intValue();
                String mobilenumber=mobilenumberET.getText().toString();
                String genderString=GenderET.getText().toString();
                int gender=0;
                if(genderString.toLowerCase()=="male")
                {
                    gender=1;
                }

                if (email.equals("") || username.equals("") || password.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Enter valid details", Toast.LENGTH_LONG).show();
                }
                else if(username.length()<5 || password.length()<5){
                    Toast.makeText(RegisterActivity.this,"Length of username or password should be greater than 5",Toast.LENGTH_LONG).show();

                }
                else if(age<0)
                {
                    Toast.makeText(RegisterActivity.this, "Enter valid Age", Toast.LENGTH_LONG).show();
                }
                else if(mobilenumber.length()!=10)
                {
                    Toast.makeText(RegisterActivity.this, "Enter valid Mobile number", Toast.LENGTH_LONG).show();
                }

                else
                {
                    registeruser(username,password,email,age,gender,mobilenumber);
                }

            }
        });
    }
    private void registeruser(final String username,final String password,final String email,final int age,final int gender,final String mobilenumber){
        new AsyncTask<Void,Void,JSONObject>() {
            DialogUtils progressDialog =new DialogUtils(RegisterActivity.this,DialogUtils.Type.PROGRESS_DIALOG);
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.showProgressDialog("Registering","please wait..",false);
            }
            @Override
            protected JSONObject doInBackground(Void... params) {
                return new ServerRequest().userRegistration(RegisterActivity.this,username,password,email,age,gender,mobilenumber);
            }



            @Override
            protected void onPostExecute(JSONObject response) {
                super.onPostExecute(response);
                progressDialog.dismissProgressDialog();
                try{
                    if (response.getString("status").equals("success")) {
                        Toast.makeText(RegisterActivity.this,"Registration successful",Toast.LENGTH_LONG).show();
                        Toast.makeText(RegisterActivity.this,"Login successful",Toast.LENGTH_LONG).show();
                        User user = new UserParser().parse(response.getJSONObject("user"));
                        loginPreferences.loginUser(user);
                    }
                    else if(response.getString("status").equals("failed")){
                        Toast.makeText(RegisterActivity.this,"Registration failed",Toast.LENGTH_LONG).show();
                        Toast.makeText(RegisterActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                    }
                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }.execute();
    }
}
