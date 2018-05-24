package fr.nelfdesign.meteonelf;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ClimatAdaptateur extends RecyclerView.Adapter<ClimatAdaptateur.ViewHolder>{

    Climat climat;

    //constructeur
    public ClimatAdaptateur(Climat climat) {
        this.climat = climat;
    }

    @Override
    //cette méthode dit à l'adaptateur à quoi ressemble le layout
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //déclare que item_climat est le template
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_climat,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    //signale quelle information il faut mettre et à quelle position
    public void onBindViewHolder(@NonNull ClimatAdaptateur.ViewHolder holder, int position) {

        ClimatInfos climatInfos = climat.getClimatInfosArrayList().get(position);
        Temp temp = climat.getTempArrayList().get(position);
        Location loc = climat.getLocation();

        holder.date_tv.setText(temp.getDate());
        holder.temp.setText((int) climatInfos.getTemp() + " °C");
        holder.loc.setText(loc.getName() + ", " + loc.getCountry());

        Context context = holder.image.getContext();
        String icon = Utilities.getIconUri(climatInfos.getId());
        int iconId = context.getResources().getIdentifier(icon,null,context.getPackageName());
        Drawable imageDrawable = context.getResources().getDrawable(iconId);
        holder.image.setImageDrawable(imageDrawable);

        // background dynamique
        Context c2 = holder.card.getContext();
        String icon2 = Utilities.getUriBack(climatInfos.getId());
        int iconId2 = c2.getResources().getIdentifier(icon2,null,c2.getPackageName());
        Drawable imageDrawable2 = c2.getResources().getDrawable(iconId2);

        holder.card.setBackground(imageDrawable2);

    }


    @Override
    //donne le nombre d'elements
    public int getItemCount() {
        //retourne le nombre avec size
        return climat.getClimatInfosArrayList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date_tv;
        TextView temp;
        TextView loc;
        ImageView image;
        ConstraintLayout card;

        public ViewHolder(final View itemView) {
            super(itemView);

            date_tv = itemView.findViewById(R.id.date_tv);
            temp = itemView.findViewById(R.id.temperature);
            loc = itemView.findViewById(R.id.loc);
            image = itemView.findViewById(R.id.img);
            card = itemView.findViewById(R.id.card);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    Context context = v.getContext();

                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra(DetailsActivity.LOCATION_CLEF, climat.location);
                    intent.putExtra(DetailsActivity.TEMPS_ARRAY, climat.tempArrayList.get(position));
                    intent.putExtra(DetailsActivity.CLIMAT_INFOS_ARRAY, climat.climatInfosArrayList.get(position));
                    context.startActivity(intent);
                }
            });
        }
    }
}
