package com.developer.annant.gopaltyres.Extras_imp;

import android.content.Context;
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

public class TyreDataAdapter extends ArrayAdapter<TyreDataVariable> {

    public TyreDataAdapter (Context context, ArrayList<TyreDataVariable> tyreDataVariable){
        super(context ,0, tyreDataVariable);

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.card_listview, parent, false);
        }

        TextView tyreSize = (TextView) listItemView.findViewById(R.id.list_treadname);
        TextView tyreType = (TextView) listItemView.findViewById(R.id.list_treadtype);
        TextView Price = (TextView) listItemView.findViewById(R.id.list_tyre_position);



        ImageView tyreImage = (ImageView) listItemView.findViewById(R.id.list_imagethumb);

        //the below statements get the text from the class TyreData

        TyreDataVariable currentTyreDataVariable = getItem(position);


        tyreSize.setText(currentTyreDataVariable.getTyreSize());
        tyreType.setText(currentTyreDataVariable.getTreadName());
        Price.setText(currentTyreDataVariable.getPrice());

        tyreImage.setImageResource(currentTyreDataVariable.getResourceImageView());


        return listItemView;
    }

}
