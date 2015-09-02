package com.reeuse.materialdesign.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.reeuse.materialdesign.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PaletteConceptActivity extends AppCompatActivity {

    private ImageView headerImage;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette_concept);
        headerImage = (ImageView) findViewById(R.id.header_img);

        setupToolbar();
        setHeaderImage();
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null)
            setSupportActionBar(toolbar);

        // to show back arrow icon
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.menu_palette));
    }


    private void setHeaderImage() {
        String url = "https://scontent-sin1-1.xx.fbcdn.net/hphotos-xlp1/v/t1.0-9/11895990_1042466722438619_8198324640250634765_n.jpg?oh=779ef89ad50cbe5edb0007c4fd22ac1c&oe=565F44EA";
        Picasso.with(this).load(url).into(headerImage, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) headerImage.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        applyPalette(palette);
                    }
                });
            }

            @Override
            public void onError() {

            }
        });
    }

    private void applyPalette(Palette palette) {
        int primary = getResources().getColor(R.color.primary);
        int primaryDark = getResources().getColor(R.color.primary_dark);
        toolbar.setBackgroundColor(palette.getMutedColor(primary));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(palette.getDarkVibrantColor(primary));
            getWindow().setNavigationBarColor(palette.getDarkMutedColor(primaryDark));
        }
        supportStartPostponedEnterTransition();
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
