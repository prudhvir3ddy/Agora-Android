package org.aossie.agoravote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {

    SharedPrefs sharedPrefs;
    private EditText mUserNameEditText, mPasswordEditText;
    private Button mSigninButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");
//        mUserNameEditText = findViewById(R.id.editText);
//        mPasswordEditText = findViewById(R.id.editText2);
        mSigninButton = findViewById(R.id.button);

        sharedPrefs = new SharedPrefs(getApplicationContext());
        mSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSignIn();
            }
        });
    }

    private void doSignIn() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("password", mPasswordEditText.getText().toString());
            jsonObject.put("identifier", mUserNameEditText.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post("https://agora-rest-api.herokuapp.com/api/v1/auth/login")
                .addJSONObjectBody(jsonObject)// posting json
                .addHeaders("Content-Type", "application/json")
                .addHeaders("accept", "application/json")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", "" + response);
                        try {
                            JSONObject token = response.getJSONObject("token");
                            String key = token.getString("token");
                            sharedPrefs.savePref(key);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("errorb", "" + error.getErrorBody());
                        Log.d("errorr", "" + error.getResponse());
                        Log.d("errord", "" + error.getErrorDetail());
                        Log.d("errorc", "" + error.getErrorCode());
                        Log.d("errorm", "" + error.getMessage());
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

        }
        return super.onOptionsItemSelected(item);
    }
}
