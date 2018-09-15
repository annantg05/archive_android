package com.developer.annant.gopaltyres.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.developer.annant.gopaltyres.R;
import com.developer.annant.gopaltyres.Extras_imp.TyreData;
import com.developer.annant.gopaltyres.Extras_imp.TyreDataAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PickUpFragment extends Fragment {


    public PickUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_list_maker, container, false);

        final ArrayList<TyreData> tyreData = new ArrayList<>();

        //Data feed Here To create ArratList

        tyreData.add(new TyreData("Super Lug 99", "7.00-15","Na" ));


        /*

        Feed Data here In ArrayList <>;


        */

        TyreDataAdapter adapter = new TyreDataAdapter(getActivity(),tyreData);

        ListView listView = (ListView) rootView.findViewById(R.id.activity_bikelist);
        listView.setAdapter(adapter);



        return rootView;

    }

}
