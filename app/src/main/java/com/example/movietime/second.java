package com.example.movietime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class second extends AppCompatActivity {
    private static final String address ="https://api.themoviedb.org/4/list/1?api_key=c5def90bdaef09f89aafd98da7f314c1" ;
    private ArrayList<list_second> listSeconds;
    private GridView gridView;
    MyAdapter adapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        gridView=(GridView)findViewById(R.id.gridView2);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
        listSeconds=new ArrayList<>();

        // adapter=new MyAdapter(getApplicationContext(),list_data);
        getData();
    }
    private void getData(){
        StringRequest stringRequest =new StringRequest(Request.Method.GET, address, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject ob = array.getJSONObject(i);
                        list_second list_seconds = new list_second(ob.getString("original_title"), ob.getString("overview"), ob.getString("release_date"), ob.getString("original_language"));
                        listSeconds.add(list_seconds);
                    }
                    Log.i("results =>", listSeconds.size() + " ");
                    adapter = new MyAdapter(getApplicationContext(), R.layout.second,listSeconds);
                    gridView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }
                catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error =>",error.toString());
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}