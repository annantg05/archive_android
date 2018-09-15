package com.developer.annant.gopaltyres.Fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.developer.annant.gopaltyres.Extras_imp.TyreDataAdapter;
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
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BikeFragment extends Fragment {


    public static final String LOG_TAG = BikeFragment.class.getSimpleName();
    final String JSON_REQUEST_URL = "https://api.myjson.com/bins/c4eqz";  /// ENTER THE URL HERE
    final String JSON_FILE_NAME = "bikedatajson.json";
    //  Bundle args = new Bundle();

    ArrayList<TyreDataVariable> tyreDataReturn = new ArrayList<>();


    public BikeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_list_maker, container, false);

        //ParseJsonResponseFromServer(JSON_URL_REQUEST);


      /*  StringRequest request = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST,JSON_URL_REQUEST,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        parseJsonData(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Some Error Occured In ParseJsonResponseFromServer ", Toast.LENGTH_LONG).show();
            }
        })      ;

        RequestQueue rQueue = Volley.newRequestQueue(getContext());
        rQueue.add(request);
*/


        //  parseJsonData(loadJSONFromAsset());


         new JsonAsyncTask(getActivity(),rootView).execute();




        /*ListView TyreDatalistView = (ListView) rootView.findViewById(R.id.activity_bikelist);

        final TyreDataAdapter adapter = new TyreDataAdapter(getContext(), tyreDataReturn);

        TyreDatalistView.setAdapter(adapter);
        */ return rootView;
    }


    // this method is a part of BikeFragment Class of parent class
    // Verified Methos **Do not Edit** //  Works on Global VAriable of type  ArrayList //  Verified 5 Times

    /**
     * STRICTLY DONT EDIT

     */




    // Async Task Below


    private class JsonAsyncTask extends AsyncTask<URL, Void, ArrayList<TyreDataVariable>> {
        private Context mContext;
        private View rootView;


        public JsonAsyncTask(Context context,View rootView){
            this.mContext=context;
            this.rootView=rootView;
        }


        @Override
        protected ArrayList<TyreDataVariable> doInBackground(URL... urls) {
            URL url = createUrl(JSON_REQUEST_URL);     //Step 1
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);   //Step 2
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "error In do In Background method ", Toast.LENGTH_LONG).show();
                // TODO Handle the IOException
            }
           // BikeFragment bikeFragment = new BikeFragment();
            ArrayList<TyreDataVariable> arrayList = parseJsonData(jsonResponse);


            return arrayList;
        }



        private void updateUi(ArrayList<TyreDataVariable> tyreDataVariables) {


            ListView TyreDatalistView = (ListView) rootView.findViewById(R.id.activity_bikelist);

            final TyreDataAdapter adapter = new TyreDataAdapter(getContext(), tyreDataVariables);

            TyreDatalistView.setAdapter(adapter);


        }


        ArrayList<TyreDataVariable> parseJsonData(String JsonResponse) {


            try {
                JSONObject jsonRootObject = new JSONObject(JsonResponse);

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
        }  //end parseJson Data Or array List maker Global









        @Override
        protected void onPostExecute(ArrayList<TyreDataVariable> tyreDataVariables) {
           //super.onPostExecute(tyreDataVariables);

        updateUi(tyreDataVariables);

        }

        //      First Function Refer Step 1 ;

        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }


        // Second Function Refer Step 2

        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000  );          // milliseconds
                urlConnection.setConnectTimeout(15000 );        // milliseconds
                urlConnection.connect();
                int responseCode = urlConnection.getResponseCode();
                if(responseCode==200) {

                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);                 // Step 3
                }
                else {
                    jsonResponse="";
                }
            } catch (IOException e) {
                Log.e(LOG_TAG,"error getting InputStrean",e);
                // TODO: Handle the exception
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



        // Third Function Refer Step 3

        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }














    }//End {@link AsyncTask}


} //end class  BikeFragment




    ///BAckup code doen below





    //<code>
    /* public void ParseJsonResponseFromServer(String JSONUrl){
         StringRequest request = new StringRequest(Request.Method.POST,JSONUrl,
                 new Response.Listener<String>() {

                     @Override
             public void onResponse(String response) {
                 parseJsonData(response);
             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(getContext(),"Some Error Occured In ParseJsonResponseFromServer ", Toast.LENGTH_LONG).show();
             }
         })      ;

         RequestQueue rQueue = Volley.newRequestQueue(getContext());
         rQueue.add(request);

        </code>
*/
















//} //

//End  Fragment





/*          Everything below is backup code to be cleanded upon sucessful execution



















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
   /* public String loadJSONFromAsset() {
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



}*/
