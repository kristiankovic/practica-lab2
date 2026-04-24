package com.pdm.video;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pdm.video.entities.Articulo;
import com.pdm.video.entities.Categoria;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout();

        db = AppDatabase.getInstance(getApplicationContext());

        AppDatabase.databaseWriteExecuto.execute(() -> {
            db.categoria_dao().insertCategoria(new Categoria("Cargadores"));
            runOnUiThread(() -> {
                Toast.makeText(this, "se inserto el dato", Toast.LENGTH_SHORT).show();
            });
        });

        navigationView = findViewById(R.id.menuButton);
        loadFragment(new HomeFragment());

        navigationView.setOnItemSelectedListener(menuItem -> {
            if(menuItem.getItemId() == R.id.home){
                //Toast.makeText(this, "inicio", Toast.LENGTH_SHORT).show();
                loadFragment(new HomeFragment());
                return true;
            } else if (menuItem.getItemId() == R.id.article) {
                //Toast.makeText(this, "articulos", Toast.LENGTH_SHORT).show();
                loadFragment(new ArticlesFragment());
                return true;
            } else if (menuItem.getItemId() == R.id.category) {
                loadFragment(new CategoryFragment());
                //Toast.makeText(this, "categorias", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

    }

    public void layout(){
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, fragment).commit();
    }
}