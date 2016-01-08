
package com.reeuse.materialdesign.customviews;

import android.app.Dialog;
import android.content.Context;

import com.reeuse.materialdesign.R;


/**
 * ProgressIndicator.java
 */
public class ProgressIndicator extends Dialog {

    CustomProgressBar customProgressBar;
    Context mContext;
    ProgressIndicator dialog;

    public ProgressIndicator(Context context) {
        super(context);
        this.mContext = context;
    }

    public ProgressIndicator(Context context, int theme) {
        super(context, theme);
    }

    public ProgressIndicator showLoading() {
        dialog = new ProgressIndicator(mContext, R.style.ProgressIndicator);
        dialog.setContentView(R.layout.loading_layout);
        customProgressBar = (CustomProgressBar) dialog.findViewById(R.id.loading_progress);
        customProgressBar.setColorSchemeResources(R.color.red, R.color.green, R.color.blue, R.color.orange);
        dialog.setCancelable(true);
        dialog.show();
        return dialog;
    }

    public ProgressIndicator hideLoading() {
        if (dialog != null) {
            dialog.dismiss();
        }
        return dialog;
    }

}