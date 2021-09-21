package com.androidrion.ptsganjil2021xirpl2steffano29.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidrion.ptsganjil2021xirpl2steffano29.R;
import com.androidrion.ptsganjil2021xirpl2steffano29.activity.DetailActivity;
import com.androidrion.ptsganjil2021xirpl2steffano29.activity.FavoriteActivity;
import com.androidrion.ptsganjil2021xirpl2steffano29.adapter.MovieAdapter;
import com.androidrion.ptsganjil2021xirpl2steffano29.model.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidnetworking.common.Priority;
import com.androidnetworking.AndroidNetworking;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private ArrayList<MovieModel> movieModels;

    private Toolbar toolbar;
    private ImageView favorite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recview);
        toolbar = view.findViewById(R.id.id_toolbar);
        favorite = view.findViewById(R.id.wishlist);
        // Inflate the layout for this fragment
        getData();

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FavoriteActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    void getData() {
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/upcoming?api_key=b9dfca59664f58a8ac10cb5506272133&language=en-US&page=1")
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .addHeaders("token", "1234")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            movieModels = new ArrayList<>();

                            JSONArray result = response.getJSONArray("results");
                            for (int i = 0; i < result.length(); i++) {
                                JSONObject movieObject = result.getJSONObject(i);

                                String title = movieObject.getString("original_title");
                                String date = movieObject.getString("release_date");
                                String desc = movieObject.getString("overview");
                                String poster = movieObject.getString("poster_path");

                                movieModels.add(new MovieModel(title, date, desc, poster));
                            }
                            adapter = new MovieAdapter(movieModels, new MovieAdapter.Callback() {
                                @Override
                                public void onClick(int position) {

                                    Intent intent = new Intent(getContext(), DetailActivity.class);
                                    intent.putExtra("original_title", movieModels.get(position).getOriginal_title());
                                    intent.putExtra("poster_path", movieModels.get(position).getPoster_path());
                                    intent.putExtra("release date", movieModels.get(position).getRelease_date());
                                    intent.putExtra("overview", movieModels.get(position).getOverview());
                                    startActivity(intent);
                                }
                            });

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();

                            Log.d("error", "onError: " + e.toString());
                        }

                        Log.d("hasil response", "onError: " + response.toString());
                    }
                    @Override
                    public void onError(ANError anError)  {
                        Log.d("error", anError.toString());
                    }
                });
    }
}