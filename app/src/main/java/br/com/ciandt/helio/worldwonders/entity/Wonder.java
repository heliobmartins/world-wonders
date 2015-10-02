package br.com.ciandt.helio.worldwonders.entity;

public class Wonder  {

    public String id;
    public String name;
    public String country;
    public String description;
    public String imageUrl;

    public Wonder(String id, String name, String country, String description) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.description = description;
    }
}
