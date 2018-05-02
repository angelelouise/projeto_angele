package com.example.angelelouise.projeto_angele;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by AngeleLouise on 30/04/2018.
 */

public class PerfilActivity extends Activity{

    private Usuario usuario_principal;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        TextView usuario = findViewById(R.id.perfil_usuario);
        TextView email = findViewById(R.id.perfil_email);
        TextView descricao = findViewById(R.id.perfil_descricao);
        TextView nome = findViewById(R.id.perfil_nome);
        ImageView perfil = findViewById(R.id.perfil_img);

        usuario.setText(usuario_principal.getLogin());
        email.setText(usuario_principal.getEmail());
        nome.setText(usuario_principal.getNome());


    }

    public void editar(View v){
        Intent intent = new Intent(PerfilActivity.this, EditActivity.class);
        startActivity(intent);
    }

}
