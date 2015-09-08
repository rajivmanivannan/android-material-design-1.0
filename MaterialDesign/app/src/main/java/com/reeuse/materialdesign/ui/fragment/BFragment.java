package com.reeuse.materialdesign.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reeuse.materialdesign.R;
import com.reeuse.materialdesign.adapter.RecyclerViewAdapter;
import com.reeuse.materialdesign.model.ProductItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BFragment extends Fragment {

    private RecyclerView recyclerView;
    public BFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        setUpRecyclerView();
        loadData();
        return view;
    }
    private void setUpRecyclerView() {

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        // allows for optimizations if all item views are of the same size:
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    private void loadData() {
        List<ProductItem> userItemList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ProductItem userItem = new ProductItem();
            userItem.setProductName("");
            userItem.setImageUrl("http://www.sammobile.com/wp-content/uploads/2013/03/fhd_03.png");
            userItemList.add(userItem);
        }

        // Load data.
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(userItemList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ProductItem viewModel) {

            }
        });
    }

}
