package com.reeuse.materialdesign.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.reeuse.materialdesign.R;
import com.reeuse.materialdesign.adapter.SlideTabAdapter;
import com.reeuse.materialdesign.ui.fragment.AFragment;
import com.reeuse.materialdesign.ui.fragment.BFragment;

public class TabLayoutActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        setupToolbar();
        setUpViewPager();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null)
            setSupportActionBar(toolbar);

        // to show back arrow icon
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.menu_tab_layout));
    }

    private void setUpViewPager() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        SlideTabAdapter slideTabAdapter = new SlideTabAdapter(getSupportFragmentManager());
        slideTabAdapter.addFragment(new AFragment(), "Tab A");
        slideTabAdapter.addFragment(new BFragment(), "Tab B");
        viewpager.setAdapter(slideTabAdapter);
        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

}
