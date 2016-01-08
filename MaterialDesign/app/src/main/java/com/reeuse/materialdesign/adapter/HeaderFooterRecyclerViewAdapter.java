package com.reeuse.materialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.reeuse.materialdesign.R;
import com.reeuse.materialdesign.model.ProductItem;

import java.util.List;

/**
 * HeaderFooterRecyclerViewAdapter.java
 */
public class HeaderFooterRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    List<ProductItem> productItem;
    Context context;

    public HeaderFooterRecyclerViewAdapter(Context context, List<ProductItem> productItem) {
        this.context = context;
        this.productItem = productItem;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false);
            return new HeaderViewHolder(v);
        } else if (viewType == TYPE_FOOTER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_item, parent, false);
            return new FooterViewHolder(v);
        } else if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
            return new ViewHolder(v);
        }
        return null;
    }

    private ProductItem getItem(int position) {
        return productItem.get(position);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            headerHolder.txtTitleHeader.setText("Header");
            headerHolder.txtTitleHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Clicked Header", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;
            footerHolder.txtTitleFooter.setText("Footer");
            footerHolder.txtTitleFooter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Clicked Footer", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof ViewHolder) {
            ProductItem currentItem = getItem(position - 1);
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.txtName.setText(currentItem.getProductName());
        }
    }

    //    need to override this method
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        } else if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position == productItem.size() + 1;
    }

    @Override
    public int getItemCount() {
        return productItem.size() + 2;
    }

    protected static class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleFooter;

        public FooterViewHolder(View itemView) {
            super(itemView);
            this.txtTitleFooter = (TextView) itemView.findViewById(R.id.footer_name_txt);
        }
    }

    protected static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleHeader;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            this.txtTitleHeader = (TextView) itemView.findViewById(R.id.header_name_txt);
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txtName = (TextView) itemView.findViewById(R.id.product_name_txt);
        }
    }
}