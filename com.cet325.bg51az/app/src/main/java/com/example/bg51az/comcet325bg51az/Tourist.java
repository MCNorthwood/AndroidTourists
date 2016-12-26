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
    public String image;
    public String geolocation;
    public double price;
    public int rank;

    public Tourist() {

    }

    //Change images to String ImageName, Object of Bitmap to store image into
    public Tourist(String name, String loc, String des, Boolean fav, String plan, String visit, String im, String geo, double price, int rank) {
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
    public String toString() {
        return ("Name: " + name +
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

    // set
    public void setName(String pName) {
        name = pName;
    }

    public void setLocation(String pLocation) {
        location = pLocation;
    }

    public void setDescription(String pDescription) {
        description = pDescription;
    }

    public void setFavourite(boolean pFavourite) {
        favourite = pFavourite;
    }

    public void setPlanned(String pPlanned) {
        planned = pPlanned;
    }

    public void setVisited(String pVisited) {
        visited = pVisited;
    }

    public void setImage(String pImage) {
        image = pImage;
    }

    public void setGeolocation(String pGeolocation) {
        geolocation = pGeolocation;
    }

    public void setPrice(double pPrice) {
        price = pPrice;
    }

    public void setRank(int pRank) {
        rank = pRank;
    }

    //get
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public String getPlanned() {
        return planned;
    }

    public String getVisited() {
        return visited;
    }

    public String getImage() {
        return image;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public double getPrice() {
        return price;
    }

    public int getRank() {
        return rank;
    }
}
