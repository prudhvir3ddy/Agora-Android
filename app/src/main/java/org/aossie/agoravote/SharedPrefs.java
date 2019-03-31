package org.aossie.agoravote;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    public static final String myPrefs = "myprefs";
    public static final String LogedInKey = "Key";
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public SharedPrefs(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(myPrefs, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void savePref(String key) {
        editor.putString(LogedInKey, key);
        editor.commit();
    }

    public String getLogedInKey() {
        return sharedPreferences.getString(LogedInKey, null);
    }
}
