package org.techtown.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
EditText et;
Button button;
static RequestQueue requestQueue;
MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=findViewById(R.id.editTextTextPersonName2);
        button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
            }
        });

        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(getApplicationContext());
        }

        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        adapter=new MovieAdapter();
        recyclerView.setAdapter(adapter);


    }

    private void makeRequest(){
        String url=et.getText().toString();
        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답=> " + response);
                        processResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                println("에러발생=> "+error.getMessage());
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
        println("요청보냄");
    }

    private void println(String str){
        Log.d("test",str);
    }

    private void processResponse(String response){
        Gson gson=new Gson();
        MovieList movieList=gson.fromJson(response,MovieList.class);
        println("영화정보수: "+movieList.boxOfficeResult.dailyBoxOfficeList.size());
        for(int i=0;i<movieList.boxOfficeResult.dailyBoxOfficeList.size();i++){
            Movie movie=movieList.boxOfficeResult.dailyBoxOfficeList.get(i);
            adapter.addItem(movie);
        }
        adapter.notifyDataSetChanged();
    }

}