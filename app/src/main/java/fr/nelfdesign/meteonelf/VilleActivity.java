package fr.nelfdesign.meteonelf;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.io.IOException;

public class VilleActivity extends AppCompatActivity implements OnMapReadyCallback{

    Button btn;
    Button btn_verif;
    EditText ville;
    Ville town;
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ville);

        btn = findViewById(R.id.btn_valider);
        btn_verif = findViewById(R.id.btn_verif);
        ville = findViewById(R.id.ville);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Editable text = ville.getText();
                String texte = text.toString();
                town = new Ville(texte);
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra(MainActivity.VILLE_CLEF, town);
                context.startActivity(intent);
            }
        });

        btn_verif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Utilities.showLatLon(ville,mMap,getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

}
