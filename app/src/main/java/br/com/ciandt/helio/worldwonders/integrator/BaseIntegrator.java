package br.com.ciandt.helio.worldwonders.integrator;

import br.com.ciandt.helio.worldwonders.infrastructure.Constants;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class BaseIntegrator {

    /**
     * Run a GET call against a rest api service.
     * P.S.: This method must be called in background.
     *
     * @param urlStr - the path of the service
     * @return the response in json format
     */
    //protected String doGetRequest(final String protocol, final String host, final String path) {
    public String doGetRequest(final String urlStr) {

        String responseData = null;
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        /*try {
            URI uri = URIUtils.createURI(protocol,
                    host, 0,
                    path, null, null);
            HttpGet get = new HttpGet(uri);
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            responseData = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try {
            //URL url = new URL(protocol + host + path);
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            //TODO: Demo - 6: Assincronismo
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("X-Parse-Application-Id", Constants.Integrator.WorldWondersApi.API_APPLICTION_ID);
            urlConnection.setRequestProperty("X-Parse-REST-API-Key", Constants.Integrator.WorldWondersApi.API_REST_KEY);
            //urlConnection.setRequestProperty("X-Parse-Session-Token", "r:g0DOqMjX8PYG4uvVVCzQPemH6");

            urlConnection.connect();

            inputStream = new BufferedInputStream(urlConnection.getInputStream());
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext()) {
                responseData = scanner.next();
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return responseData;
    }

}
