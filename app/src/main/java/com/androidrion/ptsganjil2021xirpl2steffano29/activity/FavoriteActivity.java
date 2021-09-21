package com.androidrion.ptsganjil2021xirpl2steffano29.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.androidrion.ptsganjil2021xirpl2steffano29.R;
import com.androidrion.ptsganjil2021xirpl2steffano29.adapter.FavoriteAdapter;
import com.androidrion.ptsganjil2021xirpl2steffano29.model.FavoriteModel;
import com.androidrion.ptsganjil2021xirpl2steffano29.realmclass.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavoriteAdapter adapter;
    private List<FavoriteModel> favoriteModels;

    private Realm realm;
    private RealmHelper realmHelper;

    private ImageView backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        backbutton = findViewById(R.id.home);
        recyclerView = findViewById(R.id.recview);

        //Retup realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);

        realmHelper = new RealmHelper(realm);
        favoriteModels = new ArrayList<>();

        //Mengisi data
        favoriteModels = realmHelper.getAllData();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        show();

        //menginisiasikan button untuk kembali ke fragment home
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //mengabarkan adapter ketika pengguna menambahkan favorite lagi
    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
        show();
    }

    //mengirim data dari favorite activity ke favorite detail activity saat di klik
    public void show(){
        adapter = new FavoriteAdapter(favoriteModels, new FavoriteAdapter.Callback() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getApplicationContext(), FavoriteDetailActivity.class);
                intent.putExtra("id", favoriteModels.get(position).getId());
                intent.putExtra("title", favoriteModels.get(position).getOriginal_title());
                intent.putExtra("release_data", favoriteModels.get(position).getRelease_date());
                intent.putExtra("overview", favoriteModels.get(position).getOverview());
                intent.putExtra("poster_path", favoriteModels.get(position).getPoster_path());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}