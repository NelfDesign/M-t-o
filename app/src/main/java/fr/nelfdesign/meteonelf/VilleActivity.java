package fr.nelfdesign.meteonelf;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class VilleActivity extends AppCompatActivity implements OnMapReadyCallback {

    Button btn;
    EditText ville;
    Ville town;
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ville);

        btn = findViewById(R.id.btn_valider);
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
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Nantes
        // and move the map's camera to the same location.

    }

    public void showLatLon(View view) throws IOException {
        Editable text = ville.getText();
        String texte = text.toString();
        List<Address> adresses = null;

        if (texte != null || !texte.equals("")){
            Geocoder geocoder =new Geocoder(this);
           adresses = geocoder.getFromLocationName(texte,1);
        }

        Address address = adresses.get(0);
        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng)
                .title("Marker"));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,6));
    }

}
