package com.androidrion.ptsganjil2021xirpl2steffano29.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidrion.ptsganjil2021xirpl2steffano29.model.MovieModel;
import com.androidrion.ptsganjil2021xirpl2steffano29.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<MovieModel> movieModels;
    private Callback callback;
    Context CT;
    private int position;
    private View myview;

    public interface Callback {
        void onClick(int position);
    }

    public MovieAdapter(ArrayList<MovieModel> movieModels, Callback callback) {
        this.movieModels = movieModels;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,
                parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        MovieModel movieModel = movieModels.get(position);
        holder.title.setText(movieModel.getOriginal_title());
        holder.reldate.setText(movieModel.getRelease_date());
        holder.overview.setText(movieModel.getOverview());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/original" + movieModels.get(position).getPoster_path())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.img_list);
    }

    @Override
    public int getItemCount() {
        return (movieModels != null) ? movieModels.size() : 0;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView title, reldate, overview;
        private ImageView img_list;
        CardView MainLayout;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            myview = itemView;

            title = itemView.findViewById(R.id.title);
            reldate = itemView.findViewById(R.id.reldate);
            overview = itemView.findViewById(R.id.overview);
            img_list = itemView.findViewById(R.id.img_list);
            MainLayout = (CardView) itemView.findViewById(R.id.mainlayout);

            MainLayout.setOnCreateContextMenuListener(this);

            MainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(getLayoutPosition());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Delete = menu.add(Menu.NONE, 1, 1, "Delete");
            position = getLayoutPosition();
            Delete.setOnMenuItemClickListener(clickContext);
        }
    }

    private final MenuItem.OnMenuItemClickListener clickContext = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {
                case 1:
                    AlertDialog.Builder builder = new AlertDialog.Builder(myview.getContext());
                    builder.setMessage("Are you sure you want to delete this data?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    movieModels.remove(position);
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                            .setTitle(movieModels.get(position).getOriginal_title());
                    AlertDialog alert = builder.create();
                    alert.show();

                    break;
            }
            return true;
        }
    };
}


