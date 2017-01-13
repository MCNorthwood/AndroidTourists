package com.example.bg51az.comcet325bg51az.database;

import java.util.Date;

public class Tourist
{
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
    public String notes;
    public Date planned;
    public Date visited;

    public Tourist()
    {
        planned = null;
        visited = null;
    }

    public Tourist(String name, String loc, String des, boolean fav, String im, String geo, double price, int rank, boolean delete, String notes, Date planned, Date visited)
    {
        this.name = name;
        this.location = loc;
        this.description = des;
        this.favourite = fav;
        this.image = im;
        this.geolocation = geo;
        this.price = price;
        this.rank = rank;
        this.deletable = delete;
        this.notes = notes;
        this.planned = planned;
        this.visited = visited;
    }

    @Override
    public String toString()
    {
        return ("Name: " + name +
                "\nLocation: " + location +
                "\nDescription: " + description +
                "\nFavourite: " + favourite +
                //"\nImage: " + image +
                "\nGeoLocation: " + geolocation +
                "\nPrice: " + price +
                "\nRank: " + rank +
                "\nDeletable: " + deletable /*+
                "\nNotes: " + notes +
                "\nPlanned: " + planned +
                "\nVisited: " + visited*/);
    }
}