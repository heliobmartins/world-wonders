package br.com.ciandt.helio.worldwonders.business;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.ciandt.helio.worldwonders.entity.Wonder;
import br.com.ciandt.helio.worldwonders.infrastructure.Constants;
import br.com.ciandt.helio.worldwonders.integrator.BaseIntegrator;

/**
 * Created by Hélio on 02/10/2015.
 */
public class WonderBusinessCoordinator {

    public List<Wonder> getAllWonders(){
        List<Wonder> wonderList = new ArrayList<>();
        BaseIntegrator baseIntegrator = new BaseIntegrator();
        String jsonResult = baseIntegrator.doGetRequest(Constants.Integrator.WorldWondersApi.HOST_PARSE +
                Constants.Integrator.WorldWondersApi.GET_WONDERS_LIST);

        if (jsonResult != null){
            try {
                JSONObject jsonObject = new JSONObject(jsonResult);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    Wonder wonder = new Wonder(jsonArray.getJSONObject(i));
                    wonderList.add(wonder);
                }

                return wonderList;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
