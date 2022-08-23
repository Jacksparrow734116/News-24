package com.example.news24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class NewsFullArticle extends AppCompatActivity {
String Title,Publish,Desc,Image,Content,Url;
private TextView ArticleTit,ArticlePub,ArticleDesc,ArticleCont;
private ImageView ArticleImg;
private Button buttonClick;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_article);
        Content=getIntent().getStringExtra("content");
        Url=getIntent().getStringExtra("uri");
        Title=getIntent().getStringExtra("title");
        Publish= getIntent().getStringExtra("publish");
        Desc=getIntent().getStringExtra("desc");
        buttonClick=(Button) findViewById(R.id.button);
        Image=getIntent().getStringExtra("image");
        ArticleCont=(TextView) findViewById(R.id.articleContent);
        ArticleDesc=(TextView) findViewById(R.id.articleDesc);
        ArticleTit=(TextView) findViewById(R.id.articleTitle);
        ArticlePub=(TextView) findViewById(R.id.articlePublish);
        ArticleImg=(ImageView) findViewById(R.id.articleImage);
        Glide.with(this).load(Image).into(ArticleImg);
        ArticlePub.setText(Publish);
        ArticleTit.setText(Title);
        ArticleDesc.setText(Desc);
        ArticleCont.setText(Content);
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Url));
                startActivity(i);
            }
        });
    }
}