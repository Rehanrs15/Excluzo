package com.examples.rehan.excluzo.Activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class LoginActivity extends BaseActivity {

    EditText emailET,passwordET;
    Button loginBT;
    LoginPreferences loginPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        applyFont(LoginActivity.this,findViewById(R.id.base_layout));

        loginPreferences = new LoginPreferences(LoginActivity.this);
        emailET = (EditText)findViewById(R.id.email);
        passwordET = (EditText)findViewById(R.id.password);
        loginBT = (Button)findViewById(R.id.login_button);

        loginBT.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                //get the eail and password
                if(email.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this,"Enter Valid Details",Toast.LENGTH_LONG).show();
                }
                else{
                    loginUser(email,password);
                }

            }
        });
    }

    private void loginUser(final  String email,final String password) {
        new AsyncTask<Void, Void, JSONObject>(){

            DialogUtils progressDialog = new DialogUtils(LoginActivity.this,DialogUtils.Type.PROGRESS_DIALOG);
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.showProgressDialog("Login","please wait..",false);
            }

            @Override
            protected JSONObject doInBackground(Void... params) {
                return new ServerRequest().userLogin(LoginActivity.this,email,password);
            }

            @Override
            protected void onPostExecute(JSONObject response) {
                super.onPostExecute(response);
                progressDialog.dismissProgressDialog();
                try {
                    if(response != null) {
                        if (response.getString("status").equals("success")) {
                            User user = new UserParser().parse(response.getJSONObject("result"));
                            loginPreferences.loginUser(user);
                        }
                        else if(response.getString("status").equals("failed")){
                            Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(LoginActivity.this,R.string.wrong,Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }.execute();
    }
}
