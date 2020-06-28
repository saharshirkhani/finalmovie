package com.example.movietime;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class second extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textview6;
    ImageView imageView;
   RequestQueue queue;
   private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
        }
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        textView1 = findViewById(R.id.txtid);
        textView2 = findViewById(R.id.txttitle);
        textView3 = findViewById(R.id.txtcon);
        textView4 = findViewById(R.id.txtlan);
        textView5 = findViewById(R.id.txtover);
        textview6 = findViewById(R.id.txttime);
        imageView = findViewById(R.id.imageView);

        queue = Volley.newRequestQueue(this);

        String address = "https://api.themoviedb.org/3/movie/" + id + "?api_key=c5def90bdaef09f89aafd98da7f314c1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, address, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    textView1.setText(jsonObject.getString("id"));
                    textView2.setText(jsonObject.getString("original_title"));
                    JSONArray array = jsonObject.getJSONArray("production_contries");
                    JSONObject object = array.getJSONObject(0);
                    textView3.setText(object.getString("name"));
                    textView4.setText(jsonObject.getString("original_language"));
                    textView5.setText(jsonObject.getString("overview"));
                    textview6.setText(jsonObject.getString("release_date"));
                    Picasso.with(second.this).load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + jsonObject.getString("poster_path")).into(imageView);

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error =>", error.toString());
            }
        });
        progressBar.setVisibility(View.GONE);
        queue.add(stringRequest);
    }

    }


}