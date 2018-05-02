package com.example.angelelouise.projeto_angele;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by AngeleLouise on 30/04/2018.
 */

public class MainActivity extends Activity {
    private Usuario usuario_principal;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView nome = findViewById(R.id.nome);
        TextView email = findViewById(R.id.email);
        TextView descricao = findViewById(R.id.descricao);
        ImageView perfil = findViewById(R.id.perfil_img);

        nome.setText(usuario_principal.getNome());
        email.setText(usuario_principal.getEmail());
        descricao.setText(usuario_principal.getDescricao());
        perfil.setImageBitmap(usuario_principal.getPerfil());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // return true so that the menu pop up is opened
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.perfil:
                Intent perfil = new Intent(MainActivity.this,PerfilActivity.class);
                startActivity(perfil);
                return true;
            case R.id.Logout:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
