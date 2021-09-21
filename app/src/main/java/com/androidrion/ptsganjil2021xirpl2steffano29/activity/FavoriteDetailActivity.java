package com.androidrion.ptsganjil2021xirpl2steffano29.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.androidrion.ptsganjil2021xirpl2steffano29.R;
import com.androidrion.ptsganjil2021xirpl2steffano29.realmclass.RealmHelper;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FavoriteDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Bundle bundle;
    private TextView movieTitle, description, release_date;
    private ImageView poster, backbutton;
    private Button delete;

    private int id;
    private String image, title, desc, date;;

    private Realm realm;
    private RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorite);

        movieTitle = findViewById(R.id.movieName);
        description = findViewById(R.id.Description);
        release_date = findViewById(R.id.Reldate);
        poster = findViewById(R.id.Poster);

        backbutton = findViewById(R.id.backbutton);
        delete = findViewById(R.id.btn_delete);

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

        //untuk mengabarkan apabila transfer sukses
        Log.i("ppppppppppppppp", "onCreate: "+id);

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
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(backbutton)){
            finish();
        }

        //untuk menghapus data
        if (view.equals(delete)){
            realmHelper.Delete(id);
            Toast.makeText(getApplicationContext(), "Delete success!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}