package br.com.ciandt.helio.worldwonders.manager;

import android.os.AsyncTask;

import java.util.List;

import br.com.ciandt.helio.worldwonders.business.WonderBusinessCoordinator;
import br.com.ciandt.helio.worldwonders.entity.Wonder;
import br.com.ciandt.helio.worldwonders.entity.vo.OperationError;
import br.com.ciandt.helio.worldwonders.infrastructure.Constants;
import br.com.ciandt.helio.worldwonders.integrator.BaseIntegrator;
import br.com.ciandt.helio.worldwonders.listener.OperationListener;

public class WonderManager {

    public void getAllWonders(final OperationListener<List<Wonder>> callback) {
        final BaseIntegrator integrator = new BaseIntegrator();
        /*final StringBuffer path = new StringBuffer();
        path.append(ApplicationConfiguration.getApiServer()).append(Constants.Integrator);*/

        new AsyncTask<Void, Void, List<Wonder>>() {
            @Override
            protected List<Wonder> doInBackground(Void... params) {
                WonderBusinessCoordinator wonderBusinessCoordinator = new WonderBusinessCoordinator();
                return wonderBusinessCoordinator.getAllWonders();
            }

            @Override
            protected void onPostExecute(List<Wonder> wonderList) {
                super.onPostExecute(wonderList);
                if(wonderList != null){
                    callback.onOperationSuccess(wonderList);
                } else{
                    callback.onOperationError(new OperationError("1","NullPointerExcpetion"));
                }
            }
        }.execute();

    }
}