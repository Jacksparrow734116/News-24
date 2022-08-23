package com.example.news24;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
public ArrayList<News> NewsItems;
public Context ctx;
public NewsAdapter(ArrayList<News> items, Context applicationContext){
    NewsItems=items;
    ctx=applicationContext;
}
@Override
public NewsViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
         int LayoutForStream=R.layout.adapter_list_style;
         LayoutInflater inflater=LayoutInflater.from(ctx);
         View view=inflater.inflate(LayoutForStream,parent,false);
         return new NewsViewHolder(view);
   }
   @Override
   public void onBindViewHolder(NewsAdapter.NewsViewHolder holder, int position){
    News item=NewsItems.get(position);
    holder.mNewsView.setText(item.getTitle());
    holder.mNewsview3.setText(item.getDescription());
    if(item.getAuthor()==null){
        holder.mNewsview2.setText("Anonymous");
    }else{
        holder.mNewsview2.setText(item.getAuthor());
    }
    Glide.with(ctx).load(item.getUrlToImage()).into(holder.mNewsImage);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(ctx,NewsFullArticle.class);
        i.putExtra("title",item.getTitle());
        i.putExtra("publish",item.getPublishedAt());
        i.putExtra("desc",item.getDescription());
        i.putExtra("image",item.getUrlToImage());
        i.putExtra("full",item.getUrl());
        i.putExtra("content",item.getContent());
        i.putExtra("uri",item.getUrl());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(i);
    }
    });
    }
    @Override
    public int getItemCount() {
    return NewsItems.size();
   }
    public void setArticles(ArrayList<News> newArticle) {
    if (newArticle != null && newArticle.size() > 0) {
        NewsItems = newArticle;
        notifyDataSetChanged();
        }
    }
      public class NewsViewHolder extends RecyclerView.ViewHolder {
      private TextView mNewsView;
      private TextView mNewsview2;
      private TextView mNewsview3;
      private ImageView mNewsImage;
      public NewsViewHolder(View itemView) {
              super(itemView);
              mNewsView=(TextView) itemView.findViewById(R.id.view_item);
              mNewsview2=(TextView) itemView.findViewById(R.id.view_item2);
              mNewsImage=(ImageView) itemView.findViewById(R.id.imageView);
              mNewsview3=(TextView) itemView.findViewById(R.id.view_item03);
        }
    }
}