package com.example.adams.tradersportal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuyFragment extends Fragment {

    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_buy, container, false);

        activity = getActivity();

        ListView listView = (ListView) rootView.findViewById(R.id.listView);

        setList(listView);

        return rootView;
    }

    private void setList(ListView listView) {
        DatabaseHelper databaseHelper = new DatabaseHelper(activity.getApplicationContext());
        ArrayList<SellProduct_> sellProductArrayList = databaseHelper.fetchSellProducts();
        if (sellProductArrayList.size() > 0) {
            ProductListAdapter productListAdapter = new ProductListAdapter(activity, sellProductArrayList);
            listView.setAdapter(productListAdapter);
        } else {
            Toast.makeText(getActivity(), "Records not found", Toast.LENGTH_SHORT).show();
        }
    }
}
