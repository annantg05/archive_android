package com.developer.annant.gopaltyres.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.annant.gopaltyres.Extras_imp.TyreDataVariable;
import com.developer.annant.gopaltyres.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * A simple {@link Fragment} subclass.
 */
public class BikeFragment extends Fragment {

    private static final String JSON_REQUEST_URL =""   ;                               //Enter The URL of Json File Hosted on the Internet ;

    final String JSON_FILE_NAME = "bikedatajson.json";
    //  Bundle args = new Bundle();
    public static final String  LOG_TAG = BikeFragment.class.getSimpleName() ;


    public BikeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_list_maker, container, false);

/*
        ArrayList<TyreDataVariable> tyreDataVariable = extractTyreDataVariable();

        ListView TyreDatalistView = (ListView) rootView.findViewById(R.id.activity_bikelist);

        final TyreDataAdapter adapter = new TyreDataAdapter(getContext(), tyreDataVariable);

        TyreDatalistView.setAdapter(adapter);

*/


        return rootView;

    }



/*
    public ArrayList<TyreDataVariable> extractTyreDataVariable() {

        ArrayList<TyreDataVariable> tyreDataReturn = new ArrayList<>();

        try {


            JSONObject jsonRootObject = new JSONObject(loadJSONFromAsset());


            // The two lines are for sucess full verificaton of JSON ;

            // String toShow = jsonRootArray.toString();
            // textView.setText(toShow);

            //Ignore the code above for debugging

            //Main Code for  JSON  @ Parsing is  written  below

            JSONArray tyreArray = jsonRootObject.getJSONArray("tyre");


            for (int i = 0; i < tyreArray.length(); i++) {

                JSONObject currentIndex = tyreArray.getJSONObject(i);

                String tyreSize = currentIndex.getString("size");
                String tyreTreadName = currentIndex.getString("name");
                long tyrePrice = currentIndex.getLong("price");


                TyreDataVariable tyreDataVariable = new TyreDataVariable(tyreSize, tyreTreadName, String.valueOf(tyrePrice));
                tyreDataReturn.add(tyreDataVariable);

            }


        } catch (JSONException e) {
            e.printStackTrace();
            //Toast.makeText( this, "THis Is JSON EXCEPTION ", Toast.LENGTH_LONG).show();

        }

        return tyreDataReturn;

    }
*/

    public String loadJSONFromAsset() {
        String json = null;
        // getJsonFileName();

        try {


            InputStream is = getActivity().getAssets().open(JSON_FILE_NAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }


    /// // / / /// // // // / / / / / / /          Modified Code BELOW


    public class TyreAsyncTask  extends AsyncTask<URL, Void ,TyreDataVariable> {

        @Override
        protected TyreDataVariable doInBackground(URL... urls) {
            URL url = createUrl(JSON_REQUEST_URL);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";

            try{
                jsonResponse = makeHttpRequest(url);
            }catch (IOException eio){
                eio.printStackTrace();;
            }


            // Extract relevant fields from the JSON response and create an {@link Event} object

            TyreDataVariable tyreDataVariable =   extractTyreDataVariableFromJson(jsonResponse) ;

            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            return tyreDataVariable;
        }

////////////////////////////////////////////////////////////////////////////////////////////////

        private URL createUrl(String stringUrl){
            URL url = null ;

            try{
                url = new URL(stringUrl);
            }catch (MalformedURLException exception){
                Log.e(LOG_TAG, "Error with creating URL ",exception);
                return null;
            }

            return url;
        }


        //////////////////////////////////////////////////////////////////////////////////////////////////////////

        private String makeHttpRequest(URL url) throws IOException{
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;

            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000    /* milliseconds */);
                urlConnection.setConnectTimeout(15000  /* milliseconds */);
                urlConnection.connect();
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } else {
                    jsonResponse = "";
                }
            }catch (IOException e){
                Log.e(LOG_TAG,"error getting InputStrean",e);


            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }



/////////////////////////////////////////////////////////////////// ////////////////////////////////////////////////////

        private String readFromStream(InputStream inputStream) throws IOException{

            StringBuilder output = new StringBuilder();

            if(inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while(line != null){
                    output.append(line);
                    line = reader.readLine();
                }

            }

            return output.toString();

        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        private TyreDataVariable extractTyreDataVariableFromJson(String tyreJSON){

            if(TextUtils.isEmpty(tyreJSON)){
                return null;
            }

            try{
                JSONObject baseJsonResponse = new JSONObject(tyreJSON);

                JSONArray tyreArray = baseJsonResponse.getJSONArray("tyre");



                    for (int i=0 ; i < tyreArray.length() ; i++) {

                        JSONObject currentIndex = tyreArray.getJSONObject(i);

                        String tyreSize = currentIndex.getString("size");
                        String tyreTreadName = currentIndex.getString("name");
                        long tyrePrice = currentIndex.getLong("price");

                        return new TyreDataVariable(tyreSize, tyreTreadName, String.valueOf(tyrePrice));

                    }




            }catch (JSONException ejson){
                Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", ejson);
                ejson.printStackTrace();

            }

            return null;
        }



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }//end Of AsyncTask Class






















}//  End BikeFragment
