package org.techtown.mission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
rssAdapter adapter;
TextView tv;
Button button;
ArrayList<rssItem> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new rssAdapter();
        recyclerView.setAdapter(adapter);

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Parsing parsing = new Parsing();
                ArrayList<rssItem> list = new ArrayList<rssItem>();
                try {
                    list =parsing.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                adapter.setItems(list);
                adapter.notifyDataSetChanged();
            }
        });


    }

    public class Parsing extends AsyncTask<Object,Object, ArrayList<rssItem>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<rssItem> doInBackground(Object... objects) {
            items=new ArrayList<rssItem>();
            try {
                URL url= new URL("https://www.yonhapnewstv.co.kr/browse/feed/");
                XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
                XmlPullParser parser=factory.newPullParser();
                BufferedInputStream bis = new BufferedInputStream(url.openStream());
                parser.setInput(bis, null);
                String tag="";
                String header="";
                String info="";
                boolean isItemTag=false;

                int eventType = parser.getEventType();
                while(eventType!=XmlPullParser.END_DOCUMENT){
                    switch(eventType){
                        case XmlPullParser.START_TAG:
                            tag=parser.getName();
                            if(tag.equals("item")){
                                isItemTag=true;
                            }
                            break;
                        case XmlPullParser.TEXT:
                            if(isItemTag){
                                switch(tag) {
                                    case "title":
                                        header = parser.getText();
                                    case "description":
                                        info = parser.getText();
                                }
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            tag=parser.getName();
                            if(tag.equals("item")) {
                                rssItem data=new rssItem();
                                data.header=header;
                                data.info=info;
                                items.add(data);
                                header = "";
                                info = "";
                                isItemTag=false;
                            }
                            tag = "";
                            break;

                    }
                    eventType=parser.next();
                }

            } catch (Exception e) {
                Log.d("testing",e.getMessage());
            }

            return items;
        }



        @Override
        protected void onPostExecute(ArrayList<rssItem> rssItems) {
            super.onPostExecute(rssItems);
        }

    }
}