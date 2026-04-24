package com.pdm.video;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pdm.video.daos.ArticuloDAO;
import com.pdm.video.daos.CategoriaDAO;
import com.pdm.video.entities.Articulo;
import com.pdm.video.entities.Categoria;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Categoria.class, Articulo.class},
          version = 1,
          exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CategoriaDAO categoria_dao();
    public abstract ArticuloDAO articulo_dao();

    // patron de trabajo SINGLETON
    private static volatile AppDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecuto = Executors.newFixedThreadPool(4);
    public static AppDatabase getInstance(Context context){

        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "db_prestamos").build();
                }
            }
        }

        return INSTANCE;
    }

}
