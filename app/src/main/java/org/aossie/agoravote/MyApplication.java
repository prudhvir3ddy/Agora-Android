package org.aossie.agoravote;

import android.app.Application;
import android.content.Intent;

import com.androidnetworking.AndroidNetworking;

public class MyApplication extends Application {
    SharedPrefs sharedPrefs;
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
        sharedPrefs = new SharedPrefs(this);
        if (sharedPrefs.getLogedInKey() != null)
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }
}
