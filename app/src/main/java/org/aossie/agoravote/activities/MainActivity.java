package org.aossie.agoravote.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.aossie.agoravote.R;
import org.aossie.agoravote.SharedPrefs;

public class MainActivity extends AppCompatActivity {

    SharedPrefs sharedPrefs;
    TextView mAgoraTextView;
    Button mSigninButton, mFacebookButton, mSignupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefs = new SharedPrefs(getApplicationContext());
//        doSignOut();
        mAgoraTextView = findViewById(R.id.agoratext);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/Roboto-Medium.ttf");
        mAgoraTextView.setTypeface(typeface);

        mSigninButton = findViewById(R.id.signin);
        mFacebookButton = findViewById(R.id.faceboook);
        mSignupButton = findViewById(R.id.signup);

        mFacebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Facebook login keys are not set", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        mSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
    }

//    private void doSignOut() {
//        AndroidNetworking.get("https://agora-rest-api.herokuapp.com/api/v1/user/logout")
//                .addHeaders("X-Auth-Token", sharedPrefs.getLogedInKey())
//                .addHeaders("accept", "application/json")
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsString(new StringRequestListener() {
//                    @Override
//                    public void onResponse(String response) {
//                        // do anything with response
//                        Log.d("response", "" + response);
//                        finish();
//                    }
//
//                    @Override
//                    public void onError(ANError error) {
//                        // handle error
//                        Log.d("errorb", "" + error.getErrorBody());
//                        Log.d("errorr", "" + error.getResponse());
//                        Log.d("errord", "" + error.getErrorDetail());
//                        Log.d("errorc", "" + error.getErrorCode());
//                        Log.d("errorm", "" + error.getMessage());
//                    }
//                });
//    }
}
