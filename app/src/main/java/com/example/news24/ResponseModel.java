package com.example.news24;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseModel {
        @SerializedName("status")
        private String status;
        @SerializedName("totalResults")
        private int totalResults;
        @SerializedName("articles")
        private ArrayList<News> articles = null;
        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
        public int getTotalResults() {
            return totalResults;
        }
        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }
        public ArrayList<News> getArticles() {
            return articles;
        }
        public void setArticles(ArrayList<News> articles) {
            this.articles = articles;
        }
    }
