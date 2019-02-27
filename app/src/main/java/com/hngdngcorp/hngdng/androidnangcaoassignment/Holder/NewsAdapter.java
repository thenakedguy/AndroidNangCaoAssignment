package com.hngdngcorp.hngdng.androidnangcaoassignment.Holder;

import android.content.Context;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hngdngcorp.hngdng.androidnangcaoassignment.Model.News;
import com.hngdngcorp.hngdng.androidnangcaoassignment.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewHolder> {
    Context context;
    List<News> news;

    public NewsAdapter(Context context, List<News> news) {
        this.context = context;
        this.news = news;
    }

    @NonNull
    @Override
    public NewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list,viewGroup,false);
        return new NewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewHolder newHolder, int i) {
        newHolder.news = news.get(i);
        newHolder.mTvTitle.setText(newHolder.news.title);
        newHolder.mTvPubDate.setText(newHolder.news.pubDate);

    }

    @Override
    public int getItemCount() {
        return (news == null) ? 0: news.size();
    }


    public class NewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTvTitle;
        private TextView mTvPubDate;
        News news;

        public NewHolder(@NonNull View itemView) {
            super(itemView);


            mImageView = itemView.findViewById(R.id.imageView);
            mTvTitle = itemView.findViewById(R.id.tvTitle);
            mTvPubDate = itemView.findViewById(R.id.tvPubDate);

        }
    }
}
