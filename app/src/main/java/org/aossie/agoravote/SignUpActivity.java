package org.aossie.agoravote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    private EditText mUserNameEditText, mFirstNameEditText, mLastNameEditText, mEmailEditText, mPasswordEditText;
    private Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mUserNameEditText = findViewById(R.id.username);
        mFirstNameEditText = findViewById(R.id.firstname);
        mLastNameEditText = findViewById(R.id.lastname);
        mEmailEditText = findViewById(R.id.email);
        mPasswordEditText = findViewById(R.id.password);
        mSignUpButton = findViewById(R.id.signup);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSignUp();
            }
        });

    }

    private void doSignUp() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("firstName", mFirstNameEditText.getText().toString());
            jsonObject.put("lastName", mLastNameEditText.getText().toString());
            jsonObject.put("email", mEmailEditText.getText().toString());
            jsonObject.put("password", mPasswordEditText.getText().toString());
            jsonObject.put("identifier", mUserNameEditText.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post("https://agora-rest-api.herokuapp.com/api/v1/auth/signup")
                .addJSONObjectBody(jsonObject)// posting json
                .addHeaders("Content-Type", "application/json")
                .addHeaders("accept", "application/json")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        // do anything with response
                        Log.d("response", "" + response);

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

