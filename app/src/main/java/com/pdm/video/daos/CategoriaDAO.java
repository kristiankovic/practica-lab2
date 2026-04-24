package com.pdm.video.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.pdm.video.entities.Categoria;

import java.util.List;

@Dao
public interface CategoriaDAO {
    @Query("SELECT * FROM categorias")
    List<Categoria> getCategorias();

    @Insert
    long insertCategoria(Categoria categoria);
}
