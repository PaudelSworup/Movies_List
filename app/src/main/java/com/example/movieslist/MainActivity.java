package com.example.movieslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Movies> movies;
    private String JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=caa4226c251747a5c3bf3d6bc23b2d18";
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.movieList);
        movies = new ArrayList<>();
        exctractMovies();


    }

    private void exctractMovies() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                listView.setText("Response: " + response.toString());toString
                try {
                    JSONArray movs = response.getJSONArray("results");
                    for (int i = 0; i < movs.length(); i++) {
                        try {
                            JSONObject movieObject = (JSONObject) movs.get(i);
                            Movies mov = new Movies();
                            mov.setTitle(movieObject.getString("original_title").toString());
                            mov.setType(movieObject.getString("vote_average").toString());
                            mov.setCardImg("http://image.tmdb.org/t/p/w185" + movieObject.getString("poster_path"));
                            mov.setDesc(movieObject.getString("overview").toString());

                            movies.add(mov);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(),movies);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "onErrorResponse:" + error.getMessage());
            }
        });

        queue.add(jsonObjectRequest);

    }


}