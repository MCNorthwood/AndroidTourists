package com.example.bg51az.comcet325bg51az.database;

public class Tourist {
    public int id;
    public String name;
    public String location;
    public String description;
    public boolean favourite;
    public String image;
    public String geolocation;
    public double price;
    public int rank;
    public boolean deletable;

    public Tourist() {

    }

    //Change images to String ImageName, Object of Bitmap to store image into
    public Tourist(String name, String loc, String des, boolean fav, String im, String geo, double price, int rank, boolean delete) {
        this.name = name;
        this.location = loc;
        this.description = des;
        this.favourite = fav;
        this.image = im;
        this.geolocation = geo;
        this.price = price;
        this.rank = rank;
        this.deletable = delete;
    }

    @Override
    public String toString() {
        return ("Name: " + name +
                "\nLocation: " + location +
                "\nDescription: " + description +
                "\nFavourite: " + favourite +
                "\nImage: " + image +
                "\nGeoLocation: " + geolocation +
                "\nPrice: " + price +
                "\nRank: " + rank +
                "\nDeletable: " + deletable);
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

    public void setDeletable(boolean pRank) {
        deletable = pRank;
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

    public boolean getDeletable() {
        return deletable;
    }
}
