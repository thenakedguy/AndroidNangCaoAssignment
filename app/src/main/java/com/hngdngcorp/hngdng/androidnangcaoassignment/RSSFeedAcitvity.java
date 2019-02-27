package com.hngdngcorp.hngdng.androidnangcaoassignment;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hngdngcorp.hngdng.androidnangcaoassignment.Holder.NewsAdapter;
import com.hngdngcorp.hngdng.androidnangcaoassignment.Model.News;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RSSFeedAcitvity extends BaseActivity {
    private RecyclerView mLvNews;
    LinearLayoutManager linearLayoutManager;
    private String url;
    private List<News> arrayNews;
    private String text;
    private NewsAdapter newsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssfeed_acitvity);
        initViews();
        url = "https://tinhte.vn/rss";
        LoadingTask loadingTask = new LoadingTask();
        loadingTask.execute(url);

    }

    private void initViews() {


        mLvNews = findViewById(R.id.lvNews);
        linearLayoutManager = new LinearLayoutManager(RSSFeedAcitvity.this);


    }


    private class LoadingTask  extends AsyncTask<String,Long, List<News>> {
        @Override
        protected List<News> doInBackground(String... strings) {
            arrayNews = new ArrayList<>();
            try {
                URL url = new URL(strings[0]);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                xmlPullParserFactory.setNamespaceAware(false);
                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
                xmlPullParser.setInput(inputStream,"utf-8");
                int evenType = xmlPullParser.getEventType();
                News news = null;
                String s = "";

                while (evenType != xmlPullParser.END_DOCUMENT){
                    String name = xmlPullParser.getName();
                    switch (evenType){
                        case XmlPullParser.START_TAG:
                            if (name.equalsIgnoreCase("item")){
                                news = new News();
                            }
                            break;
                        case XmlPullParser.TEXT:
                            text = xmlPullParser.getText();
                            break;
                        case XmlPullParser.END_TAG:
                            if(news !=null && name.equalsIgnoreCase("title")){
                                news.title = text;
                            }else if(news !=null && name.equalsIgnoreCase("description")){
                                news.description = text;
                            }else if(news !=null && name.equalsIgnoreCase("pubDate")){
                                news.pubDate = text;
                            }else if(news !=null && name.equalsIgnoreCase("link")){
                                news.link = text;
                            }else if(name.equalsIgnoreCase("item")){
                                arrayNews.add(news);
                            }
                            break;
                    }
                    evenType = xmlPullParser.next();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }


            return arrayNews;
        }
        @Override
        protected void onPostExecute(List<News> news) {
            super.onPostExecute(news);
            //Xu ly du lieu len man hinh tai day
            setRecyclerView(news);
        }
    }
    void setRecyclerView(List<News> news){
        newsAdapter = new NewsAdapter(RSSFeedAcitvity.this,news);
        mLvNews.setLayoutManager(linearLayoutManager);
        mLvNews.hasFixedSize();
        mLvNews.setAdapter(newsAdapter);


    }
}
