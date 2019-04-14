package org.aossie.agoravote;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    private static final String myPrefs = "myprefs";
    private static final String LogedInKey = "Key";
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;


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

    public void clearLogin() {
        editor.putString(LogedInKey, null);
        editor.commit();
    }
}
