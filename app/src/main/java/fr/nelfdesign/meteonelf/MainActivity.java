package fr.nelfdesign.meteonelf;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ClimatAdaptateur climatAdaptateur;

    ImageButton btn_ville;

    public static String VILLE_CLEF = "Ville";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_ville = findViewById(R.id.btn_ville);
        btn_ville.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VilleActivity.class);
                startActivity(intent);
            }
        });

        //initialisation des varaibles
        recyclerView = findViewById(R.id.recyclerview);

        //initialisation de la requete
        new RequestCinqJours().execute();

    }



    private class RequestCinqJours extends AsyncTask<Void, Void, Climat>{

        public String ville;
        Climat climat = null;

        @Override
        protected Climat doInBackground(Void... voids) {
            Ville town = (Ville) getIntent().getSerializableExtra(VILLE_CLEF);
            Uri.Builder uri = null;

            if (town == null){
                ville = "Paris";
                uri = Utilities.getUrl(ville);
            }else {
                ville = town.getName().toString();
                uri = Utilities.getUrl(ville);
            }

            URL url = null;
            try {
                url = new URL(uri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String body = response.body().string();
                Location loc = parseLocation(body);

                 climat =  parseMain(body);
                 climat.setLocation(loc);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return climat;
        }

        @Override
        protected void onPostExecute(Climat climat) {
            super.onPostExecute(climat);

            climatAdaptateur = new ClimatAdaptateur(climat);
            //definition de la facon dont seront mise les vues
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            //on le passe au recyclerView
            recyclerView.setLayoutManager(layoutManager);
            //on definit une petite animation par default
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            //puis on definit l'adapteur au recyclerView
            recyclerView.setAdapter(climatAdaptateur);
        }
    }

    private Climat parseMain(String body) throws JSONException {
        JSONObject json = new JSONObject(body);
        JSONArray list = json.getJSONArray("list");

        ArrayList<Temp> tempArray = new ArrayList<>();
        ArrayList<ClimatInfos> climatArray = new ArrayList<>();

        for (int i =0 ; i<list.length(); i++) {
            JSONObject elementi = list.getJSONObject(i);
            Temp tempi = parseTemps(elementi);

            //si le premier element est supérieur à 15h alors on le prend
            if (i == 0 && Integer.valueOf(tempi.dt_text.substring(11,13)) > 15){
                tempArray.add(tempi);
                climatArray.add(parseClimat(elementi));
            }
            //pour les autres elements si le temp = 15h on les met dans le tableau
            if (tempi.dt_text.substring(11,13).equals("15")){
                tempArray.add(tempi);
                climatArray.add(parseClimat(elementi));
            }
        }
        Climat climat = new Climat(tempArray,climatArray);
        return climat;
    }

    private ClimatInfos parseClimat(JSONObject element0) throws JSONException {

        JSONObject main = element0.getJSONObject("main");
        float temperature = (float) main.getDouble("temp");
        float temp_min = (float) main.getDouble("temp_min");
        float temp_max = (float) main.getDouble("temp_max");
        float pressure = (float) main.getDouble("pressure");
        int humidity = main.getInt("humidity");
        JSONArray weatherarray = element0.getJSONArray("weather");
        JSONObject temp = weatherarray.getJSONObject(0);
        String weather = temp.getString("main");

        int id = temp.getInt("id");
        JSONObject wind = element0.getJSONObject("wind");
        float vent = (float) wind.getDouble("speed");

        ClimatInfos climatInfos = new ClimatInfos(id, temperature, temp_max, temp_min,pressure, vent, humidity, weather);
        return climatInfos;
    }

    private Temp parseTemps(JSONObject element0) throws JSONException {

        int unix = element0.getInt("dt");
        String date =Utilities.date(unix,"EEEE dd MMMM YYYY") ;
        String dt_text = element0.getString("dt_txt");

        Temp temp = new Temp(unix,date, dt_text);
        return temp;
    }

    private Location parseLocation(String body) throws JSONException {

            JSONObject json = new JSONObject(body);
            JSONObject city = json.getJSONObject("city");
            String name = city.getString("name");
            int id = city.getInt("id");
            float lat = (float) city.getJSONObject("coord").getDouble("lat");
            float lon = (float) city.getJSONObject("coord").getDouble("lon");

            String country = city.getString("country");

            Location loc = new Location(id, lat, lon, name, country);
            return loc;
    }

}
