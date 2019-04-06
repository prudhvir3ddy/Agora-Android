package org.aossie.agoravote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    Button mGetStartedButton;
    TextView mWelcomeTextView;
    ImageView mAgoraImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mGetStartedButton = findViewById(R.id.getstarted);
        mWelcomeTextView = findViewById(R.id.welcome_text);
        mAgoraImageView = findViewById(R.id.agora_image);

        mGetStartedButton.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_from_bottom));
        mWelcomeTextView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_from_bottom));
        mAgoraImageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_from_top));
    }

    public void getStarted(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
