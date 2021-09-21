package com.androidrion.ptsganjil2021xirpl2steffano29.realmclass;

import android.util.Log;

import com.androidrion.ptsganjil2021xirpl2steffano29.model.FavoriteModel;
import com.androidrion.ptsganjil2021xirpl2steffano29.model.MovieModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    private Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    //Function untuk menyimpan data
    public void Save(FavoriteModel movieModel){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){

                    Number current_id = realm.where(FavoriteModel.class).max("id");
                    int nextId;
                    if (current_id == null){
                        nextId = 1;
                    }
                    else {
                        nextId = current_id.intValue() + 1;
                    }

                    movieModel.setId(nextId);
                    FavoriteModel model = realm.copyToRealm(movieModel);

                    Log.d("Create", "execute: Database telah dibuat");
                }
                else {
                    Log.d("Create", "execute: Database gagal dibuat");
                }
            }
        });
    }

    //Function untuk memanggil data
    public List<FavoriteModel> getAllData(){
        RealmResults<FavoriteModel> results = realm.where(FavoriteModel.class).findAll();

        return results;
    }

    //Function untuk menghapus data
    public void Delete(int id){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<FavoriteModel> results = realm.where(FavoriteModel.class).equalTo("id", id).findAll();
                Log.i("pppppppppppppppp", "getAllData: "+results.size());
                results.deleteFromRealm(0);
            }
        });
    }
}
