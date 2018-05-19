package fr.nelfdesign.meteonelf;

import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    public static String LOCATION_CLEF = "Location";
    public static String TEMPS_ARRAY = "TempArray";
    public static String CLIMAT_INFOS_ARRAY = "ClimatInfosArray";

    private TextView date;
    private TextView temperature;
    private TextView vent;
    private TextView humidity;
    private TextView pression;
    private TextView ville;
    private ImageView imageView;
    private ConstraintLayout card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Location loc = (Location) getIntent().getSerializableExtra(LOCATION_CLEF);
        Temp temp = (Temp) getIntent().getSerializableExtra(TEMPS_ARRAY);
        ClimatInfos climatInfos = (ClimatInfos) getIntent().getSerializableExtra(CLIMAT_INFOS_ARRAY);

        imageView = findViewById(R.id.icon);
        date = findViewById(R.id.date);
        temperature = findViewById(R.id.temperature);
        vent = findViewById(R.id.vent);
        humidity = findViewById(R.id.humidity);
        pression = findViewById(R.id.pression);
        ville = findViewById(R.id.ville);
        card = findViewById(R.id.card);

        String icon = Utilities.getIconUri(climatInfos.getId());
        int iconId = getResources().getIdentifier(icon,null,getPackageName());
        Drawable imageDrawable = getResources().getDrawable(iconId);
        imageView.setImageDrawable(imageDrawable);

        // background dynamique
        String icon2 = Utilities.getUriBack(climatInfos.getId());
        int iconId2 = getResources().getIdentifier(icon2,null,getPackageName());
        Drawable imageDrawable2 = getResources().getDrawable(iconId2);
        card.setBackground(imageDrawable2);

        date.setText(temp.getDate());
        temperature.setText("Temp: " + climatInfos.getTemp() + " °C ");
        vent.setText("Vent: " + climatInfos.getVent() + " mps ");
        humidity.setText("Humidité: " + climatInfos.getHumidity() + " % ");
        pression.setText("Pression: " + climatInfos.getPressure() + " hPa ");
        ville.setText(loc.getName() + " , " + loc.getCountry());

    }
}
