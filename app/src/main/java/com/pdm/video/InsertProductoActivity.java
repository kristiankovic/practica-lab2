package com.pdm.video;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.pdm.video.adapters.CategoriaAdapter;
import com.pdm.video.entities.Articulo;
import com.pdm.video.entities.Categoria;

import java.util.List;

public class InsertProductoActivity extends AppCompatActivity {
    private AppDatabase db;
    public List<Categoria> data;
    public RecyclerView list_categorias;
    public CategoriaAdapter adapter;
    public Categoria categoria;
    public TextInputEditText txtDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout();
        list_categorias = findViewById(R.id.list_categorias);
        txtDescripcion = findViewById(R.id.txtInputDescripcion);

        db = AppDatabase.getInstance(getApplicationContext());

        AppDatabase.databaseWriteExecuto.execute(() -> {
            //db.categoria_dao().insertCategoria(new Categoria("Cargadores"));

            data = db.categoria_dao().getCategorias();

            runOnUiThread(() -> {
                //Toast.makeText(this, "se inserto el dato", Toast.LENGTH_SHORT).show();
                adapter = new CategoriaAdapter(getApplicationContext(), data, (categoria) -> {
                    Toast.makeText(this, "Id categoria: " + String.valueOf(categoria.idCategoria), Toast.LENGTH_SHORT).show();
                    categoria = categoria;
                });
                list_categorias.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                list_categorias.setAdapter(adapter);
            });
        });

    }

    public void layout(){
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insert_producto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void insertArticulo(View view) {

        AppDatabase.databaseWriteExecuto.execute(() -> {

            Articulo a = new Articulo();
            String descripcion = txtDescripcion.getText().toString().trim();

            if(descripcion.isEmpty()){
                txtDescripcion.setError("Ingrese una descripcion");
            }

            else{
                a.idCategoria = categoria.idCategoria;
                a.descripcion = descripcion;
                db.articulo_dao().insertArticulo(a);
            }

            runOnUiThread(() -> {
                Toast.makeText(this, "se inserto el dato", Toast.LENGTH_SHORT).show();
            });
        });
    }
}