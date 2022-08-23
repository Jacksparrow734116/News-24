package com.example.news24;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder>{
    Context context;
ArrayList<Ctegory> mCategoryAraayList;
CategoryClickListener categoryClick;
    public CategoryAdapter(Context context, ArrayList<Ctegory> mCategoryAraayList,CategoryClickListener categoryClick) {
        this.context = context;
        this.mCategoryAraayList = mCategoryAraayList;
        this.categoryClick = categoryClick;
    }
    @Override
    public CategoryHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        int Streamlayout=R.layout.category_liststyle;
        LayoutInflater Linflater=LayoutInflater.from(context);
        View view=Linflater.inflate(Streamlayout,parent,false);
        return new CategoryHolder(view);
    }
public interface CategoryClickListener{
        void clickListener(int position);
}
    @Override
    public void onBindViewHolder(CategoryAdapter.CategoryHolder Holder, int position) {
 Ctegory CategoryView=mCategoryAraayList.get(position);
 Holder.mCategory.setText(CategoryView.getCategory());
        Glide.with(context).load(CategoryView.getImageId()).into(Holder.mCatImage);
       Holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               categoryClick.clickListener(position);
           }
       });
    }

    @Override
    public int getItemCount() {
        return mCategoryAraayList.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {
        private TextView mCategory;
        private ImageView mCatImage;

        public CategoryHolder(View view) {
            super(view);
           mCategory=(TextView) view.findViewById(R.id.categoryText);
           mCatImage=(ImageView) view.findViewById(R.id.catagoryimage);
        }
    }
}
