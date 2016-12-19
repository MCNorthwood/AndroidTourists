package com.example.bg51az.comcet325bg51az;

/**
 * Created by MCNorthwood on 11/12/2016.
 */
public class Tourist {
    public int id;
    public String name;
    public String location;
    public String description;
    public Boolean favourite;
    public String planned;
    public String visited;
    public byte[] image;
    public String geolocation;
    public double price;
    public int rank;

    public Tourist(){

    }

    //Change images to String ImageName, Object of Bitmap to store image into
    public Tourist(String name, String loc, String des, Boolean fav, String plan, String visit, byte[] im, String geo, double price, int rank){
        this.name = name;
        this.location = loc;
        this.description = des;
        this.favourite = fav;
        this.planned = plan;
        this.visited = visit;
        this.image = im;
        this.geolocation = geo;
        this.price = price;
        this.rank = rank;
    }

    @Override
    public String toString(){
        return("Name: " + name +
            "\nLocation: " + location +
            "\nDescription: " + description +
            "\nFavourite: " + favourite +
            "\nPlanned: " + planned +
            "\nVisited: " + visited +
            "\nImage: " + image +
            "\nGeoLocation: " + geolocation +
            "\nPrice: " + price +
            "\nRank: " + rank);
    }
}
