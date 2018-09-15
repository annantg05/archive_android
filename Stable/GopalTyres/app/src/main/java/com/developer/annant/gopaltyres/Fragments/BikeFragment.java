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
public class BikeFragment extends Fragment {


    public BikeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_list_maker, container, false);

        final ArrayList<TyreData> tyreData = new ArrayList<>();

        //Data feed Here To create ArratList

        // 2.75-17
        tyreData.add(new TyreData("Rib", "2.75-17 ","1250" ));
        tyreData.add(new TyreData("Zapper FS", "2.75-17 ","1300" ));


        //2.75-18
        tyreData.add(new TyreData("Meteror-M", "2.75-18 ","1500"));
        tyreData.add(new TyreData("NGP", "2.75-18 ","1500"  ));
        tyreData.add(new TyreData("Moto - D", "2.75-18 ","1500"  ));
        //Front
        tyreData.add(new TyreData("ZFS", "2.75-18 ","1400"  ));
        tyreData.add(new TyreData("Rib", "2.75-18 ","1300"  ));



        //3.00-17
        //Only Rear
        tyreData.add(new TyreData("Meteror-M", "3.00-17","1500"));
        tyreData.add(new TyreData("NGP", "3.00-17","1400"  ));
        tyreData.add(new TyreData("Moto - D", "3.00-17","1450"  ));

        //3.00-18
        //Only Rear
        tyreData.add(new TyreData("Meteror-M", "3.00-18","1600"));
        tyreData.add(new TyreData("NGP", "3.00-18","1600"  ));
        tyreData.add(new TyreData("Moto - D", "3.00-18","1600"  ));

//        tyreData.add(new TyreData("NGP", "2.75-18 ","Rs  - 1500"  ));



       // tyreData.add(new TyreData("Rib", "2.75-17 ","Rs  - 1250" ));
        //0tyreData.add(new TyreData("", "2.75-18 ","Rs  - 1500" ,R.drawable.meteror_m ));


        /*

        Feed Data here In ArrayList <>;


        */

        TyreDataAdapter adapter = new TyreDataAdapter(getActivity(),tyreData);

        ListView listView = (ListView) rootView.findViewById(R.id.activity_bikelist);
        listView.setAdapter(adapter);



        return rootView;

    }

}
