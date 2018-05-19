package fr.nelfdesign.meteonelf;

import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utilities {

    private static String soleil = "@drawable/soleil2";
    private static String soleil_nuage = "@drawable/soleilnuage";
    private static String nuageux = "@drawable/nuageux";
    private static String pluiefine = "@drawable/raindrops";
    private static String pluie = "@drawable/pluie";
    private static String tempete = "@drawable/orage";
    private static String neige = "@drawable/neige";
    private static String brouillard = "@drawable/brouillard";
    private static String nuagecasse = "@drawable/gris";
    private static String soleilback = "@drawable/soleil";
    private static String nuagecasse2 = "@drawable/gris2";
    private static String nuageux2 = "@drawable/nuage";
    private static String neige2 = "@drawable/neige2";
    private static String orage = "@drawable/orage2";
    private static String soleil_nuage2 = "@drawable/ciel";
    private static String brouillard2 = "@drawable/brouillard2";
    private static String pluie2 = "@drawable/pluie2";
    private static String pluiefine2 = "@drawable/pluiefine";

    public static int convertCelcius(double temp) {
        return (int) ((temp - 32) / 1.8);
    }

    public static String date(long dt, String formatString){
        SimpleDateFormat format = new SimpleDateFormat(formatString, Locale.FRENCH);
        return format.format(new Date(dt * 1000));
    }

    public static String getIconUri(int iconId){
            //variable par defaut
            String toReturn = "@drawable/terre";
        if (iconId>=200 && iconId<=232){
            toReturn = tempete;
        }else if (iconId>=300 && iconId<=321){
            toReturn = pluiefine;
        }else if (iconId>=500 && iconId<=504){
            toReturn = pluie;
        }else if(iconId == 511){
            toReturn = neige;
        }else if (iconId>=520 && iconId<=531){
            toReturn = pluiefine;
        }else if (iconId>=600 && iconId<=622){
            toReturn = neige;
        }else if (iconId>=701 && iconId<=781){
            toReturn = brouillard;
        }else if (iconId == 800){
            toReturn = soleil;
        }else if (iconId == 801){
            toReturn = soleil_nuage;
        }else if (iconId == 802){
            toReturn = nuageux;
        }else if (iconId == 803 || iconId == 804){
            toReturn = nuagecasse;
        }
        return toReturn;
    }

    public static String getUriBack(int iconId){
        //variable par defaut
        String back = " ";
        if (iconId>=200 && iconId<=232){
            back = orage;
        }else if (iconId>=300 && iconId<=321){
            back = pluiefine2;
        }else if (iconId>=500 && iconId<=504){
            back = pluie2;
        }else if(iconId == 511){
            back = neige2;
        }else if (iconId>=520 && iconId<=531){
            back = pluiefine2;
        }else if (iconId>=600 && iconId<=622){
            back = neige2;
        }else if (iconId>=701 && iconId<=781){
            back = brouillard2;
        }else if (iconId == 800){
            back = soleilback;
        }else if (iconId == 801){
            back = soleil_nuage2;
        }else if (iconId == 802){
            back = nuageux2;
        }else if (iconId == 803 || iconId == 804){
            back = nuagecasse2;
        }
        return back;
    }

    public static Uri.Builder getUrl(String ville){
        final String apiKey = "b897bd90cec3266cc192725f3f7ef37e";
        final String unit = "metric";

        Uri.Builder uriBuilder = new Uri.Builder();
        final String queryParam = "q";
        final String appidParam = "APPID";
        final String Metric = "units";

         uriBuilder.scheme("http")
                .authority("api.openweathermap.org")
                .appendPath("data")
                .appendPath("2.5")
                .appendPath("forecast")
                .appendQueryParameter(queryParam, ville)
                .appendQueryParameter(Metric,unit)
                .appendQueryParameter(appidParam, apiKey)
                .build();

         return uriBuilder;
    }
}
