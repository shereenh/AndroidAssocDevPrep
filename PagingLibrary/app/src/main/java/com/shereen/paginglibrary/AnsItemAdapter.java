package com.shereen.paginglibrary;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shereen.paginglibrary.data.entity.ans.AnsItem;

/**
 * Created by shereen on 1/4/19
 */

public class AnsItemAdapter extends PagedListAdapter<AnsItem, AnsItemAdapter.AnsItemViewHolder> {

    private Context context;

    protected AnsItemAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public AnsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.ans_item, parent, false);
        return new AnsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnsItemViewHolder holder, int position) {

        AnsItem ansItem = getItem(position);

        if (ansItem != null) {

            Glide.with(context)
                    .load(ansItem.owner.profile_image)
                    .into(holder.imageView);

            holder.textView.setText(ansItem.owner.display_name);

        } else {
            Toast.makeText(context, "AnsItem is null", Toast.LENGTH_LONG).show();
        }

    }


    private static DiffUtil.ItemCallback<AnsItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<AnsItem>() {
                @Override
                public boolean areItemsTheSame(AnsItem oldAnsItem, AnsItem newAnsItem) {
                    return oldAnsItem.answer_id == newAnsItem.answer_id;
                }

                @Override
                public boolean areContentsTheSame(AnsItem oldAnsItem, AnsItem newAnsItem) {
                    return oldAnsItem.equals(newAnsItem);
                }
            };


    class AnsItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public AnsItemViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textViewName);
        }
    }
}
