package com.pdm.video.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.pdm.video.entities.Articulo;

import java.util.List;

@Dao
public interface ArticuloDAO {

    @Query("SELECT * FROM articulos")
    List<Articulo> getArticulos();

    @Insert
    long insertArticulo(Articulo articulo);
}
