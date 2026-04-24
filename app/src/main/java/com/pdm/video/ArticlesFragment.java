package com.pdm.video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ArticlesFragment extends Fragment {
    private FloatingActionButton btnInsertar;
    private ActivityResultLauncher<Intent> launcherInsert;

    public static ArticlesFragment newInstance(String param1, String param2) {
        ArticlesFragment fragment = new ArticlesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launcherInsert = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           if(result.getResultCode() == Activity.RESULT_OK){
               Log.d("KEY", "evento ejecutado");
           }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         View view = inflater.inflate(R.layout.fragment_articles, container, false);
         btnInsertar = view.findViewById(R.id.btnInsertar);

         btnInsertar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //Toast.makeText(getContext(), "Prueba", Toast.LENGTH_SHORT).show();
                 Intent intento = new Intent(getContext(), InsertProductoActivity.class);
                 launcherInsert.launch(intento);
             }
         });
        return view;
    }
}