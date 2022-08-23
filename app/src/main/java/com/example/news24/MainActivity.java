package com.example.news24;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.CategoryClickListener {
    private static final String API_KEY ="bd96813a0ab14095ba915da414043f0b";
    RecyclerView mRecycler;
    RecyclerView mRecycler02;
    NewsAdapter mNews;
    CategoryAdapter categoryadap;
    ArrayList<News> earthquakes;
    ArrayList<Ctegory> categoryList;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler02=(RecyclerView) findViewById(R.id.recycler_View01);
        mRecycler = (RecyclerView) findViewById(R.id.recycler_View);
        progressBar=(ProgressBar) findViewById(R.id.progress);
        earthquakes=new ArrayList<>();
        categoryList=new ArrayList<>();
        mRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecycler02.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        // setting layout manager to our recycler view.
        getCategories();
        categoryadap=new CategoryAdapter(this,categoryList,this::clickListener);
        mRecycler02.setAdapter(categoryadap);
        fetchNews("HeadLines");
    }
    private void getCategories(){
        categoryList.add(new Ctegory("https://www.background-pictures.com/wp-content/uploads/2021/06/red-background-0262-1200x960.jpeg","HeadLines"));
        categoryList.add(new Ctegory("https://dejabags.net/wp-content/uploads/2020/10/vintage-projector-800-768x583.jpg","Entertainment"));
        categoryList.add(new Ctegory("https://www.glantaf.cymru/ckfinder/userfiles/images/pe.jpg","Sports"));
        categoryList.add(new Ctegory("https://www.desktopbackground.org/p/2015/07/16/980348_business-wallpaper-35-jpg_5028x3744_h.jpg","Business"));
        categoryList.add(new Ctegory("https://cdn.hipwallpaper.com/i/78/28/380W9M.jpg","Technology"));
        categoryList.add(new Ctegory("https://png.pngtree.com/thumb_back/fh260/back_pic/03/59/82/4957a4a51714b83.jpg","Health"));
    }
    //https://th.bing.com/th/id/OIP.h4AlFl4Fq0CjqEcrLyBKpQHaEo?pid=ImgDet&rs=1","HeadLines
    private void fetchNews(String category){
        String url="https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apiKey=bd96813a0ab14095ba915da414043f0b";
        String url2="https://newsapi.org/v2/top-headlines?country=in&apiKey=bd96813a0ab14095ba915da414043f0b";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")

                .addConverterFactory(GsonConverterFactory.create())

                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<ResponseModel> call;
        if(category.equals("HeadLines")) {
           call = retrofitAPI.Headlines(url2);
        }else{
           call = retrofitAPI.CategoryNews(url);
        }
       call.enqueue(new Callback<ResponseModel>() {
           @Override
           public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
               progressBar.setVisibility(View.INVISIBLE);
               mRecycler.setVisibility(View.VISIBLE);
               if(response.body().getStatus().equals("ok")) {
                 earthquakes = response.body().getArticles();
                   if(earthquakes.size()>0) {
                     mNews = new NewsAdapter(earthquakes,getApplicationContext());
                     mRecycler.setAdapter(mNews);
                   }
               }
           }

           @Override
           public void onFailure(Call<ResponseModel> call, Throwable t) {
               progressBar.setVisibility(View.INVISIBLE);
               Toast toast=Toast.makeText(getApplicationContext(),"hbfdhkj",Toast.LENGTH_SHORT);
               toast.show();
           }
           });
    }
    @Override
    public void clickListener(int position) {
        progressBar.setVisibility(View.VISIBLE);
        mRecycler.setVisibility(View.INVISIBLE);
        earthquakes.clear();
        String x = categoryList.get(position).getCategory();
        fetchNews(x);
        if (earthquakes.size() > 0) {
            mNews.notifyDataSetChanged();
        }
    }
}