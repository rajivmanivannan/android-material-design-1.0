package com.reeuse.materialdesign.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.reeuse.materialdesign.R;
import com.reeuse.materialdesign.ui.fragment.FloatingActionButtonFragment;
import com.reeuse.materialdesign.ui.fragment.HomeFragment;
import com.reeuse.materialdesign.ui.fragment.SnackbarFragment;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        setUpAppBar();
        setUpToolBar();
        setUpNavigationDrawer();
        setUpFragment(new HomeFragment());

    }

    private void setUpAppBar() {
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        appBarLayout.animate();
    }

    private void setUpToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //To show the default menu icon
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.tool_bar);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        /**
         * To animate the menu to arrow and arrow to menu back while tapping the home menu.
         */
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.tool_bar, R.string.tool_bar) {
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
                syncState();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                syncState();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    private void setUpNavigationDrawer() {
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_widget:
                        setUpFragment(new HomeFragment());
                        break;
                    case R.id.menu_fab:
                        setUpFragment(new FloatingActionButtonFragment());
                        break;
                    case R.id.menu_snack_bar:
                        setUpFragment(new SnackbarFragment());
                        break;
                    case R.id.menu_recycler:
                        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                        break;
                    case R.id.menu_collapsing_tb:
                        startActivity(new Intent(MainActivity.this, CollasableTBActivity.class));
                        break;
                    case R.id.menu_tab_layout:
                        startActivity(new Intent(MainActivity.this, TabLayoutActivity.class));
                        break;
                    case R.id.menu_palette:
                        startActivity(new Intent(MainActivity.this, PaletteConceptActivity.class));
                        break;
                    case R.id.sub_menu_settings:
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void setUpFragment(Fragment fragment) {
        if (fragment != null) {
            String fragmentName = fragment.getClass().getSimpleName();
            FragmentManager fragmentManager = getSupportFragmentManager();
            boolean isFragmentPopped = fragmentManager.popBackStackImmediate(fragmentName, 0);

            if (!isFragmentPopped && fragmentManager.findFragmentByTag(fragmentName) == null) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, fragment, fragmentName);
                fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
                fragmentTransaction.commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        } else if (item.getItemId() == R.id.action_search) {
            //implementation for search
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1)
            ActivityCompat.finishAffinity(this);
        else
            super.onBackPressed();
    }

}
