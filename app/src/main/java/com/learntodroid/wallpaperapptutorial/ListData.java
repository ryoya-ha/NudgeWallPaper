package com.learntodroid.wallpaperapptutorial;

public class ListData {
    int id;
    int type;
    int HYFT;
    String jp_name;
    String en_name;
    double latitude;
    double longitude;
    String address;
    String DataURL;

    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return type;
    }

    public void setHYFT(int HYFT){
        this.HYFT = HYFT;
    }
    public int getHYFT(){
        return HYFT;
    }

    public void setJp_name(String jp_name){
        this.jp_name = jp_name;
    }
    public String getJp_name(){
        return jp_name;
    }

    public void setEn_name(String en_name){
        this.en_name = en_name;
    }
    public String getEn_name(){
        return en_name;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    public double getLatitude(){
        return latitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }
    public double getLongitude() {
        return longitude;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public void setDataURL(String DataURL){
        this.DataURL = DataURL;
    }
    public String getDataURL() {
        return DataURL;
    }
}
