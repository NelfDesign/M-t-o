package fr.nelfdesign.meteonelf;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class VilleActivity extends AppCompatActivity {

    Button btn;
    EditText ville;
    Ville town;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ville);

        btn = findViewById(R.id.btn_valider);
        ville = findViewById(R.id.ville);
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
}
