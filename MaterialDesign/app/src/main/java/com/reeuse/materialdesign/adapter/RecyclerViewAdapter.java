/**
 * @category Contus
 * @package com.reeuse.materialdesign.adapter
 * @version 1.0
 * @author Contus Team <developers@contus.in>
 * @copyright Copyright (C) 2015 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */
package com.reeuse.materialdesign.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.reeuse.materialdesign.R;
import com.reeuse.materialdesign.model.ProductItem;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private List<ProductItem> items;
    private OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(List<ProductItem> items) {
        this.items = items;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductItem item = items.get(position);
        holder.productName.setText(item.getProductName());
        holder.productImage.setImageBitmap(null);
       // Picasso.with(holder.productImage.getContext()).load(item.getImageUrl()).into(holder.productImage);
        holder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickListener.onItemClick(v, (ProductItem) v.getTag());
                }
            }, 200);
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView productImage;
        public TextView productName;

        public ViewHolder(View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.product_image_img);
            productName = (TextView) itemView.findViewById(R.id.product_name_txt);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, ProductItem viewModel);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}