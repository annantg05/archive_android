package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by PCEH on 12-03-2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";


    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItemView = convertView;
        if (ListItemView == null) {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }
        Earthquake currentEarthquake = getItem(position);







//Display magnitude
       double mag = currentEarthquake.getMagnitude();

        DecimalFormat formatter = new DecimalFormat("0.0");
        String oneplacemag = formatter.format(mag);




        TextView magnitudeTextView = (TextView) ListItemView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(oneplacemag);


        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(mag);

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        // Display @Date And Time In just One text View

        long timeInMilliSeconds = currentEarthquake.getTimeInMilliSeconds();

        Date dateObject = new Date(timeInMilliSeconds);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy  \n HH:mm:ss z");
        String dateAndtime = dateFormatter.format(dateObject);


        TextView DateAndTimeTextView = (TextView) ListItemView.findViewById(R.id.date_and_time);
        DateAndTimeTextView.setText(dateAndtime);

        // Display offsetLocation  and PrimaryLocation


        // get the location string

        String originalLocation = currentEarthquake.getLocation();

        // split it

        String primaryLocation = "";
        String offset_location = "";

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            //Split the string as an array of the strings
            String[] splitStrings = originalLocation.split(LOCATION_SEPARATOR);
            //Then the strings which contains (EX: 5km North of Cario Egypt ) will become (5 Km North) + of ,
            //then we store this into offset location .
            offset_location = splitStrings[0] + LOCATION_SEPARATOR;
            //Then primary location will be (Cairo , Egypt)
            primaryLocation = splitStrings[1];

        } else {
            offset_location = "Near of ";
            primaryLocation = originalLocation;
        }


        // done split location


        // set the values in TextView


        TextView offsetLocationTextView = (TextView) ListItemView.findViewById(R.id.location_offset);
        offsetLocationTextView.setText(offset_location);


        TextView primaryLocationTextView = (TextView) ListItemView.findViewById(R.id.primary_location);
        primaryLocationTextView.setText(primaryLocation);


        return ListItemView;
    }



    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }


}

