package br.com.ciandt.helio.worldwonders.infrastructure;

import br.com.ciandt.helio.worldwonders.ui.activity.BuildConfig;

/**
 * Created by Hélio on 02/10/2015.
 */
public class ApplicationConfiguration {
    public static String getApiServer(){
        return BuildConfig.API_SERVER;
    }
}
