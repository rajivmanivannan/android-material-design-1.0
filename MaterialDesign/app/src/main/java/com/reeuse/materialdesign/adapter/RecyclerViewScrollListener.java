package com.reeuse.materialdesign.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * RecyclerViewScrollListener.java
 */
public abstract class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    private int mScrollDist = 0;
    private boolean mIsVisible = true;
    private static final float MINIMUM = 25;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (mIsVisible && mScrollDist > MINIMUM) {
            hide();
            mScrollDist = 0;
            mIsVisible = false;
        } else if (!mIsVisible && mScrollDist < -MINIMUM) {
            show();
            mScrollDist = 0;
            mIsVisible = true;
        }
        if ((mIsVisible && dy > 0) || (!mIsVisible && dy < 0)) {
            mScrollDist += dy;
        }
    }

    protected abstract void hide();

    protected abstract void show();
}