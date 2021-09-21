package com.androidrion.ptsganjil2021xirpl2steffano29.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidrion.ptsganjil2021xirpl2steffano29.model.FavoriteModel;
import javax.security.auth.callback.Callback;

import com.androidrion.ptsganjil2021xirpl2steffano29.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>{

    List<FavoriteModel> favorite;
    Callback callback;

    public interface Callback{
        void onClick(int position);
    }

    public FavoriteAdapter(List<FavoriteModel> favorite, Callback callback) {
        this.favorite = favorite;
        this.callback = callback;
    }

    @NonNull
    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layout = LayoutInflater.from(parent.getContext());
        View view = layout.inflate(R.layout.itemview_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavoriteViewHolder holder, int position) {
        holder.title.setText(favorite.get(position).getOriginal_title());
        holder.reldate.setText(favorite.get(position).getRelease_date());
        holder.overview.setText(favorite.get(position).getOverview());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/original" + favorite.get(position).getPoster_path())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.img_list);
    }

    @Override
    public int getItemCount() {return (favorite != null) ? favorite.size() : 0;
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder{

        private TextView title, reldate, overview;
        private ImageView img_list;
        CardView MainLayout;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.title);
            reldate = itemView.findViewById(R.id.reldate);
            overview = itemView.findViewById(R.id.overview);
            img_list = itemView.findViewById(R.id.img_list);
            MainLayout = (CardView) itemView.findViewById(R.id.mainlayout);

            MainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onClick(getLayoutPosition());
                }
            });
        }
        }
    }
