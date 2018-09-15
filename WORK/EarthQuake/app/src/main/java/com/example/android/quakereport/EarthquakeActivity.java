/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    public static final String JSON_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";


    public ArrayList<Earthquake>  earthquake = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout

    EarthquakeAsyncTask asyncTask = new EarthquakeAsyncTask();
       asyncTask.execute(JSON_URL);

        //remember always use OnItemClickListner and its subclasses containing  OnItem in the code when you override in listitem





    }



     public class EarthquakeAsyncTask extends AsyncTask<String,Void,ArrayList<Earthquake>> {


        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {

            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            // Perform the HTTP request for earthquake data and process the response.
            ArrayList<Earthquake> result = Utils.fetchEarthquakeData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> result) {
            if (result == null) {
                return;
            }
            updateUi(result);
        }


         private void updateUi(ArrayList<Earthquake> earthquake) {


             ListView earthquakeListView = (ListView) findViewById(R.id.list);

             // Create a new {@link ArrayAdapter} of earthquakes

             final EarthquakeAdapter adapter = new EarthquakeAdapter(getApplicationContext(), earthquake);
             // Set the adapter on the {@link ListView}
             // so the list can be populated in the user interface
             earthquakeListView.setAdapter(adapter);


             earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                 @Override
                 public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                     Earthquake currentEarthquake = adapter.getItem(position);

                     // Convert the String URL into a URI object (to pass into the Intent constructor)
                     Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                     // Create a new intent to view the earthquake URI
                     Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                     // Send the intent to launch a new activity
                     startActivity(websiteIntent);


                 }


             });

         }

        }

    }











