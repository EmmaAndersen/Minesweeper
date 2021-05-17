
package com.example.minesweeper;

import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DadJoke {
    private String rep = "";



    public DadJoke() {

    }
    public void ConnectionAPI(TextView textView) {

        OkHttpClient client = new OkHttpClient();
        String url = "https://dad-jokes.p.rapidapi.com/random/joke";//"https://reqres.in/api/users?page=2";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key", "598a6219d8msh7db487f981018bdp1f92a5jsna6c74dc55e2d")
                .addHeader("x-rapidapi-host", "dad-jokes.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                   /* JSONObject json = null;
                    try {
                        json = new JSONObject(myResponse);
                        textView.setText(json.getJSONObject("data").getString("setup")+ " "+json.getJSONObject("data").getString("punchline"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/
                     rep = myResponse.substring(myResponse.indexOf("setup") + 8, myResponse.indexOf("punchline") - 3)
                          + "\n" + myResponse.substring(myResponse.indexOf("punchline") + 12, myResponse.length() - 4);
                   textView.post(new Runnable() {
                       @Override
                       public void run() {
                           textView.setText(rep);
                       }
                   });
                }
            }
        });
    }
}