package com.hitherejoe.androidboilerplate.data.model;

public class Character {

    public int id;
    public String name;
    public String description;
    public Thumbnail thumbnail;


    public class Thumbnail {
        public String path;
        public String extension;
    }
}
