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

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
                Location loc = Utilities.parseLocation(body);
                 climat =  Utilities.parseMain(body);
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
}
