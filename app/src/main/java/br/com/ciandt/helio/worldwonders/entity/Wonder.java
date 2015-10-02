package br.com.ciandt.helio.worldwonders.entity;

import org.json.JSONException;
import org.json.JSONObject;

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

    public Wonder (JSONObject jsonObject) throws JSONException{
        this.name = jsonObject.getString("name");
        this.country = jsonObject.getString("country");
        this.description = jsonObject.getString("description");
        this.imageUrl = jsonObject.getString("imageUrl");
    }
}
