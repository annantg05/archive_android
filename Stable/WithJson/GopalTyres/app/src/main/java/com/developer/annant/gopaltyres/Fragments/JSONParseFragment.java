package com.developer.annant.gopaltyres.Fragments;

import android.support.v4.app.Fragment;

import com.developer.annant.gopaltyres.Extras_imp.TyreDataVariable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class JSONParseFragment extends Fragment {

 //   public   String JSON_FILE_NAME ;
   // public String JSONFileAssetName ;

     public JSONParseFragment() {
        // Required empty public constructor
    }

/*
public void JSONFileNameFromFragment(String theNamePassesString){
    JSONFileAssetName = theNamePassesString;
}

    public String  getJsonFileName(){

        JSON_FILE_NAME = JSONFileAssetName;
        return JSON_FILE_NAME;
    }

*/

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
                int tyrePrice = currentIndex.getInt("price");


                TyreDataVariable tyreDataVariable = new TyreDataVariable(tyreSize, tyreTreadName, String.valueOf(tyrePrice));
                tyreDataReturn.add(tyreDataVariable);

            }


        } catch (JSONException e) {
            e.printStackTrace();
            //Toast.makeText( this, "THis Is JSON EXCEPTION ", Toast.LENGTH_LONG).show();

        }

        return tyreDataReturn;

    }


    public String loadJSONFromAsset() {
        String json = null;
       // getJsonFileName();

        try {


            InputStream is = getActivity().getAssets().open("bikedatajson.json");
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




}
