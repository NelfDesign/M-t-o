package fr.nelfdesign.meteonelf;

import java.io.Serializable;

public class Location implements Serializable{
    protected int id;
    protected float lat;
    protected float lon;
    protected String name;
    protected String country;
    String nouvelleVille = null;

    public Location(int id, float lat, float lon, String name, String country) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.country = country;
    }

    public Location(String nouvelleVille) {
        this.nouvelleVille = nouvelleVille;
    }

    public String getNouvelleVille() {
        return nouvelleVille;
    }

    public void setNouvelleVille(String nouvelleVille) {
        this.nouvelleVille = nouvelleVille;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
