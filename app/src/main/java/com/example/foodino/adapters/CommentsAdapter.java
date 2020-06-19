package com.example.foodino.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodino.R;
import com.example.foodino.models.Comment;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    List<Comment> comments;
    Context context;

    public CommentsAdapter(List<Comment> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_item , parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //set values to each row
        Comment comment = comments.get(position);

        holder.commentTv.setText(comment.getText());
        holder.nameTv.setText(comment.getUserName());
        holder.dateTv.setText(comment.getDate());

        if (comment.getProfileImage() != null)
            Picasso.get().load(comment.getProfileImage()).error(R.drawable.default_profile_image).into(holder.profileImage);
        else
            holder.profileImage.setImageResource(R.drawable.default_profile_image);

        //setting emoji imageView src and color based on rate value
        if (comment.getRate() == 1) {

            holder.emoji.setImageResource(R.drawable.angry);
            holder.emoji.setColorFilter(ContextCompat.getColor(context, R.color.angryColor));

        } else if (comment.getRate() == 2){

            holder.emoji.setImageResource(R.drawable.weird);
            holder.emoji.setColorFilter(ContextCompat.getColor(context, R.color.weirdColor));

        }else{

            holder.emoji.setImageResource(R.drawable.smile);
            holder.emoji.setColorFilter(ContextCompat.getColor(context, R.color.happyColor));
        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView commentTv;
        TextView nameTv;
        TextView dateTv;
        CircleImageView profileImage;
        ImageView emoji;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            commentTv = itemView.findViewById(R.id.comment_tv);
            nameTv = itemView.findViewById(R.id.name_tv);
            dateTv = itemView.findViewById(R.id.date_tv);
            profileImage = itemView.findViewById(R.id.profile_image);
            emoji = itemView.findViewById(R.id.emoji_image);
        }
    }
}
