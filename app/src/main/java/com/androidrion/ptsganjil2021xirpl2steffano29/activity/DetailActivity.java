package com.androidrion.ptsganjil2021xirpl2steffano29.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.androidrion.ptsganjil2021xirpl2steffano29.R;
import com.androidrion.ptsganjil2021xirpl2steffano29.model.FavoriteModel;
import com.androidrion.ptsganjil2021xirpl2steffano29.model.MovieModel;
import com.androidrion.ptsganjil2021xirpl2steffano29.realmclass.RealmHelper;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Bundle bundle;
    private TextView movieTitle, description, release_date;
    private ImageView poster, backbutton;
    private Button save;

    private int id;
    private String image, title, desc, date;;

    private FavoriteModel model;
    private Realm realm;
    private RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        movieTitle = findViewById(R.id.movieName);
        description = findViewById(R.id.Description);
        release_date = findViewById(R.id.Reldate);
        poster = findViewById(R.id.Poster);

        backbutton = findViewById(R.id.back_home);
        save = findViewById(R.id.btn_save);

        //Setup Realm
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(realmConfiguration);
        realmHelper = new RealmHelper(realm);

        //mengambil bundle yang sudah dibuat dari favoriteactivity
        bundle = getIntent().getExtras();
        if (bundle != null){
            id = bundle.getInt("id", 0);
            title = bundle.getString("original_title");
            desc = bundle.getString("overview");
            date = bundle.getString("release_date");
            image = bundle.getString("poster_path");
        }

        //memasangkan data-data yang sudah dikirim dalam bundle ke tempat layout yang tepat
        movieTitle.setText(title);
        description.setText(desc);
        release_date.setText(date);
        Picasso.get()
                .load(image)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_launcher_foreground)
                .into(poster);

        backbutton.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(backbutton)){
            finish();
        }

        //Button save
        if (v.equals(save)){

            bundle = getIntent().getExtras();
            if (bundle != null){
                id = bundle.getInt("id", 0);
                title = bundle.getString("original_title");
                desc = bundle.getString("overview");
                date = bundle.getString("release_date");
                image = bundle.getString("poster_path");
            }


            model = new FavoriteModel();
            model.setOriginal_title(title);
            model.setRelease_date(date);
            model.setOverview(desc);
            model.setPoster_path(image);

            realmHelper = new RealmHelper(realm);
            realmHelper.Save(model);


            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}