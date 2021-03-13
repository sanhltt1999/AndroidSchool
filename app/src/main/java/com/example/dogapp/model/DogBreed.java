package com.example.dogapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DogBreed implements Serializable {

    @SerializedName("bred_for")
    private String bredFor;

    @SerializedName("breed_group")
    private String breedGroup;

    @SerializedName("height")
    private Height height;

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    @SerializedName("life_span")
    private String lifeSpan;
    @SerializedName("origin")
    private String origin;

    @SerializedName("url")
    private String urlDog;

    @SerializedName("temperament")
    private String temperament;

    @SerializedName("weight")
    private DogWeight weight;

    public DogBreed(String bredFor, String breedGroup, Height height, int id, String name, String lifeSpan, String origin, String urlDog, String temperament, DogWeight weight) {
        this.bredFor = bredFor;
        this.breedGroup = breedGroup;
        this.height = height;
        this.id = id;
        this.name = name;
        this.lifeSpan = lifeSpan;
        this.origin = origin;
        this.urlDog = urlDog;
        this.temperament = temperament;
        this.weight = weight;
    }

    public String getBredFor() {
        return bredFor;
    }

    public void setBredFor(String bredFor) {
        this.bredFor = bredFor;
    }

    public String getBreedGroup() {
        return breedGroup;
    }

    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public DogWeight getWeight() {
        return weight;
    }

    public void setWeight(DogWeight weight) {
        this.weight = weight;
    }

    public String getUrlDog() {
        return urlDog;
    }

    public void setUrlDog(String urlDog) {
        this.urlDog = urlDog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }
}
