package com.reeuse.materialdesign.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reeuse.materialdesign.R;
import com.reeuse.materialdesign.adapter.RecyclerViewAdapter;
import com.reeuse.materialdesign.model.UserItem;

import java.util.ArrayList;
import java.util.List;


public class FloatingActionButtonFragment extends Fragment {

    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FloatingActionButtonFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fab, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        // allows for optimizations if all item views are of the same size:
        recyclerView.setHasFixedSize(true);

        List<UserItem> userItemList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            UserItem userItem = new UserItem();
            userItem.setUserName("Android Bot");
            userItem.setImageUrl("http://files.softicons.com/download/social-media-icons/little-retros-icons-by-xniikk/png/50x50/Mycolorscreen_a.png");
            userItem.setTotalPoints("100");
            userItemList.add(userItem);
        }

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(userItemList);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

}
