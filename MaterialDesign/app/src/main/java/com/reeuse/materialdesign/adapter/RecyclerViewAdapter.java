
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


    public void add(ProductItem item, int position) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(ProductItem item) {
        int position = items.indexOf(item);
        items.remove(position);
        notifyItemRemoved(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, ProductItem viewModel);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}