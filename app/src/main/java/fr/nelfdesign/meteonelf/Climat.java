package fr.nelfdesign.meteonelf;

import java.util.ArrayList;

public class Climat {

    ArrayList<Temp> tempArrayList;
    ArrayList<ClimatInfos> climatInfosArrayList;
    Location location;

    public Climat(ArrayList<Temp> tempArrayList, ArrayList<ClimatInfos> climatInfosArrayList) {
        this.tempArrayList = tempArrayList;
        this.climatInfosArrayList = climatInfosArrayList;
    }

    public ArrayList<Temp> getTempArrayList() {
        return tempArrayList;
    }

    public void setTempArrayList(ArrayList<Temp> tempArrayList) {
        this.tempArrayList = tempArrayList;
    }

    public ArrayList<ClimatInfos> getClimatInfosArrayList() {
        return climatInfosArrayList;
    }

    public void setClimatInfosArrayList(ArrayList<ClimatInfos> climatInfosArrayList) {
        this.climatInfosArrayList = climatInfosArrayList;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
