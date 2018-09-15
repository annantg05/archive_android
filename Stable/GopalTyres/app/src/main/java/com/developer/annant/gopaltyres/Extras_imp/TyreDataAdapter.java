package com.developer.annant.gopaltyres.Extras_imp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.annant.gopaltyres.R;

import java.util.ArrayList;

/**
 * Created by Annant on 05-01-2017.
 */

public class TyreDataAdapter extends ArrayAdapter<TyreData> {

    public TyreDataAdapter (Activity context, ArrayList<TyreData> tyreData){
        super(context ,0, tyreData);

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.card_listview, parent, false);
        }

        TextView tyreType = (TextView) listItemView.findViewById(R.id.list_treadtype);
        TextView tyreSize = (TextView) listItemView.findViewById(R.id.list_treadname);
        TextView Price = (TextView) listItemView.findViewById(R.id.list_tyre_position);
        ImageView tyreImage = (ImageView) listItemView.findViewById(R.id.list_imagethumb);

        //the below statements get the text from the class TyreData
        TyreData currentTyreData = getItem(position);
        tyreType.setText(currentTyreData.getTreadType());

        tyreSize.setText(currentTyreData.getTyreSize());

        Price.setText(currentTyreData.getPrice());

        tyreImage.setImageResource(currentTyreData.getResourceImageView());


        return listItemView;
    }

}
