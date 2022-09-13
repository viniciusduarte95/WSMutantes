package com.example.wsmutantes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listar(View view){
        startActivity(new Intent(this, Listar.class));
    }
    public void pesquisar(View view){
        startActivity(new Intent(this, Pesquisar.class));
    }

}