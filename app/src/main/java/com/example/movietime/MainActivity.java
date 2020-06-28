package com.example.movietime;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
public class MainActivity extends AppCompatActivity {

    private static final String HI ="https://api.themoviedb.org/4/list/1?api_key=c5def90bdaef09f89aafd98da7f314c1" ;
    private ArrayList<List_data>list_data;
    private GridView gridView;
    MyAdapter adapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView=(GridView)findViewById(R.id.gridView);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        list_data=new ArrayList<>();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List_data data=list_data.get(position);
                Intent intent=new Intent(MainActivity.this,second.class);
                intent.putExtra("id",data.getName());
                startActivity(intent);
            }
        });

        // adapter=new MyAdapter(getApplicationContext(),list_data);
        getData();
    }

    private void getData() {
        StringRequest stringRequest =new StringRequest(Request.Method.GET, HI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("results");
                    for (int i=0; i<array.length(); i++){
                        JSONObject ob=array.getJSONObject(i);
                        List_data listData=new List_data(ob.getString("id"),ob.getString("poster_path"));
                        list_data.add(listData);
                    }
                    Log.i("results =>",list_data.size() + " ");
                    adapter=new MyAdapter(getApplicationContext(), R.layout.grid_list,list_data);
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
