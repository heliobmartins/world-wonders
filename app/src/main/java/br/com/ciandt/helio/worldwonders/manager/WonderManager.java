package br.com.ciandt.helio.worldwonders.manager;

import android.os.AsyncTask;

import br.com.ciandt.helio.worldwonders.entity.vo.OperationError;
import br.com.ciandt.helio.worldwonders.infrastructure.Constants;
import br.com.ciandt.helio.worldwonders.integrator.BaseIntegrator;
import br.com.ciandt.helio.worldwonders.listener.OperationListener;

public class WonderManager {

    public void getAllWonders(final OperationListener<String> callback) {
        final BaseIntegrator integrator = new BaseIntegrator();
        /*final StringBuffer path = new StringBuffer();
        path.append(ApplicationConfiguration.getApiServer()).append(Constants.Integrator);*/

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                return integrator.doGetRequest(Constants.Integrator.WorldWondersApi.HOST_PARSE +
                        Constants.Integrator.WorldWondersApi.GET_WONDERS_LIST);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(s != null){
                    callback.onOperationSuccess(s);
                } else{
                    callback.onOperationError(new OperationError("1","NullPointerExcpetion"));
                }
            }
        }.execute();

    }
}