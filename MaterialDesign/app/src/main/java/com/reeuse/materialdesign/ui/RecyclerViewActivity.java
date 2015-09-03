
package com.reeuse.materialdesign.ui;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.reeuse.materialdesign.R;
import com.reeuse.materialdesign.adapter.RecyclerViewAdapter;
import com.reeuse.materialdesign.model.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        setupToolbar();
        setUpRecyclerView();
        loadData();
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null)
            setSupportActionBar(toolbar);
        // to show back arrow icon
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.menu_recycler));
    }


    private void setUpRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recycler, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_switch:
                if (staggeredGridLayoutManager.getSpanCount() == 1) {
                    item.setIcon(R.drawable.ic_view_grid);
                    staggeredGridLayoutManager.setSpanCount(2);
                } else {
                    item.setIcon(R.drawable.ic_view_list);
                    staggeredGridLayoutManager.setSpanCount(1);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
