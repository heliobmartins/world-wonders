package br.com.ciandt.helio.worldwonders.database.table;

public interface WondersTable {

    String TABLE_NAME = "wonders";

    String _ID = "_id";
    String ID = "id";
    String NAME = "name";
    String COUNTRY = "country";
    String DESCRIPTION = "description";
    String IMAGE_URL = "image_url";

    String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ( " + _ID
            + " INTEGER" + "," + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT" + "," + NAME + " TEXT"
            + "," + COUNTRY + " TEXT" + "," + DESCRIPTION
            + " TEXT" + "," + IMAGE_URL + " TEXT" + ")";
}