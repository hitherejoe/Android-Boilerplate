package com.hitherejoe.androidboilerplate.data.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Character {

    public String name;
    public String height;
    public String mass;
    @SerializedName("hair_color")
    public String hairColor;
    @SerializedName("skin_color")
    public String skinColor;
    @SerializedName("eye_color")
    public String eyeColor;
    @SerializedName("birth_year")
    public String birthYear;
    public String gender;
    public String homeworld;
    public List<String> films;
    public List<String> species;
    public List<String> vehicles;
    public List<String> starships;

    public Character() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Character character = (Character) o;

        if (name != null ? !name.equals(character.name) : character.name != null) return false;
        if (height != null ? !height.equals(character.height) : character.height != null)
            return false;
        if (mass != null ? !mass.equals(character.mass) : character.mass != null) return false;
        if (hairColor != null ? !hairColor.equals(character.hairColor) : character.hairColor != null)
            return false;
        if (skinColor != null ? !skinColor.equals(character.skinColor) : character.skinColor != null)
            return false;
        if (eyeColor != null ? !eyeColor.equals(character.eyeColor) : character.eyeColor != null)
            return false;
        if (birthYear != null ? !birthYear.equals(character.birthYear) : character.birthYear != null)
            return false;
        if (gender != null ? !gender.equals(character.gender) : character.gender != null)
            return false;
        if (homeworld != null ? !homeworld.equals(character.homeworld) : character.homeworld != null)
            return false;
        if (films != null ? !films.equals(character.films) : character.films != null) return false;
        if (species != null ? !species.equals(character.species) : character.species != null)
            return false;
        if (vehicles != null ? !vehicles.equals(character.vehicles) : character.vehicles != null)
            return false;
        return !(starships != null ? !starships.equals(character.starships) : character.starships != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (mass != null ? mass.hashCode() : 0);
        result = 31 * result + (hairColor != null ? hairColor.hashCode() : 0);
        result = 31 * result + (skinColor != null ? skinColor.hashCode() : 0);
        result = 31 * result + (eyeColor != null ? eyeColor.hashCode() : 0);
        result = 31 * result + (birthYear != null ? birthYear.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (homeworld != null ? homeworld.hashCode() : 0);
        result = 31 * result + (films != null ? films.hashCode() : 0);
        result = 31 * result + (species != null ? species.hashCode() : 0);
        result = 31 * result + (vehicles != null ? vehicles.hashCode() : 0);
        result = 31 * result + (starships != null ? starships.hashCode() : 0);
        return result;
    }
}
