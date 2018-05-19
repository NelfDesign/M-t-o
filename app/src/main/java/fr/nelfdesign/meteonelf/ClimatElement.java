package fr.nelfdesign.meteonelf;

class ClimatElement {
    private String ville;
    private String pays;
    private String location;
    private String tempMax;
    private String tempMin;
    private int timestamp;
    private String nomDuJour;
    private String nomDuMois;
    private int jour;
    private int mois;
    private int annee;
    private String date;

    public ClimatElement(String ville, String pays, String location,
                         String tempMax, String tempMin, int timestamp,
                         String nomDuJour, String nomDuMois,
                         int jour, int mois, int annee, String date) {
        this.ville = ville;
        this.pays = pays;
        this.location = location;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.timestamp = timestamp;
        this.nomDuJour = nomDuJour;
        this.nomDuMois = nomDuMois;
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.date = date;
    }

    public String getVille() {
        return ville;
    }

    public String getTempMax() {
        return tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public String getLocation() {
        return location;
    }

    public String getPays() {
        return pays;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getNomDuJour() {
        return nomDuJour;
    }

    public void setNomDuJour(String nomdujour) {
        this.nomDuJour = nomdujour;
    }

    public String getNomDuMois() {
        return nomDuMois;
    }

    public void setNomDuMois(String nomdumois) {
        this.nomDuMois = nomdumois;
    }
    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
