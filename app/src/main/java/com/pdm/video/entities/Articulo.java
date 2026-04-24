package com.pdm.video.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "articulos", foreignKeys = {
        @ForeignKey(entity = Categoria.class,
        parentColumns = "idCategoria",
        childColumns = "idCategoria",
        onDelete = ForeignKey.RESTRICT,
        onUpdate = ForeignKey.NO_ACTION)})
public class Articulo {
    @PrimaryKey(autoGenerate = true)
    public int idArticulo;
    @ColumnInfo(name = "descripcion_articulo")
    public String descripcion;
    public int idCategoria;
}
