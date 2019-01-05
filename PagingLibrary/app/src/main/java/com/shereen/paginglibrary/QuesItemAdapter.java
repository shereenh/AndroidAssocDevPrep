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
import com.shereen.paginglibrary.data.entity.ques.QuesItem;

/**
 * Created by shereen on 1/5/19
 */

public class QuesItemAdapter extends PagedListAdapter<QuesItem, QuesItemAdapter.QuesItemViewHolder> {

    private Context context;

    protected QuesItemAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public QuesItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.ques_item, viewGroup, false);

        return new QuesItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuesItemAdapter.QuesItemViewHolder itemViewHolder, int i) {

        QuesItem quesItem = getItem(i);

        if (quesItem != null) {

            Glide.with(context)
                    .load(quesItem.owner.profile_image)
                    .into(itemViewHolder.imageView);
            itemViewHolder.textViewName.setText(quesItem.owner.display_name);
            itemViewHolder.textViewQues.setText(quesItem.title);
        } else{
            Toast.makeText(context, "QuesItem is null", Toast.LENGTH_LONG).show();
        }
    }

    private static final DiffUtil.ItemCallback<QuesItem> diffCallback =
            new DiffUtil.ItemCallback<QuesItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull QuesItem oldQuesItem, @NonNull QuesItem newQuesItem) {
                    return oldQuesItem.question_id == newQuesItem.question_id;
                }

                @Override
                public boolean areContentsTheSame(@NonNull QuesItem oldQuesItem, @NonNull QuesItem newQuesItem) {
                    return oldQuesItem.equals(newQuesItem);
                }
            };

    class QuesItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName;
        TextView textViewQues;

        public QuesItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewQues = itemView.findViewById(R.id.questionView);
        }
    }
}
