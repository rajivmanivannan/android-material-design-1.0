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
import android.widget.TextView;

import com.reeuse.materialdesign.R;
import com.squareup.picasso.Picasso;

import com.reeuse.materialdesign.model.UserItem;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private List<UserItem> items;
    private OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(List<UserItem> items) {
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        UserItem item = items.get(position);
        holder.userName.setText(item.getUserName());
        holder.totalPoints.setText(item.getTotalPoints());
        holder.profileImage.setImageBitmap(null);
        Picasso.with(holder.profileImage.getContext()).load(item.getImageUrl()).into(holder.profileImage);
        holder.itemView.setTag(item);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    @Override public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    onItemClickListener.onItemClick(v, (UserItem) v.getTag());
                }
            }, 200);
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView profileImage;
        public TextView userName;
        public TextView totalPoints;
        public ViewHolder(View itemView) {
            super(itemView);
            profileImage = (CircleImageView) itemView.findViewById(R.id.profile_image);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            totalPoints = (TextView) itemView.findViewById(R.id.total_points);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, UserItem viewModel);
    }
}