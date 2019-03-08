package org.aossie.agoravote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("firstName", "Amit");
            jsonObject.put("lastName", "Shekhar");
            jsonObject.put("email", "hello@alpha-web.net");
            jsonObject.put("password", "prudhvi99");
            jsonObject.put("identifier", "arannnnn");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("ge", "" + jsonObject);
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
}
