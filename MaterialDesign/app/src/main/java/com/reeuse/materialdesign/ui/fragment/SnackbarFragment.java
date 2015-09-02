package com.reeuse.materialdesign.ui.fragment;


import android.os.Bundle;


import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.reeuse.materialdesign.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SnackbarFragment extends Fragment {



    public SnackbarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_snackbar, container, false);

        Button showSnackbar = (Button)view.findViewById(R.id.snackbar_button);

        showSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpSnackBar();
            }
        });
        return view;
    }

    void setUpSnackBar(){
        Snackbar.make(getView().findViewById(R.id.root_framelayout), "This is Snackbar", Snackbar.LENGTH_LONG)

                .setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();
    }


}
