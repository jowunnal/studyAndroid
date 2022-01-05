package org.techtown.request;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
static RequestQueue requestQueue;
TextView tv;
EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.textView);
        et=findViewById(R.id.editTextTextPersonName2);
        Button button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
            }
        });

        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
    }

    private void makeRequest(){
        String url=et.getText().toString();
        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러발생=>"+error);
                    }
                });

        request.setShouldCache(false);
        requestQueue.add(request);
        println("요청보냄");
    }


    private void println(String str){
        tv.append(str+"\n");
    }

    public void processResponse(String response){
        Gson gson=new Gson();
        MovieList movieList=gson.fromJson(response,MovieList.class);
        println("영화 정보의 수:"+movieList.boxOfficeResult.dailyBoxOfficeList.size());
    }
}