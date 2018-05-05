package com.example.angelelouise.projeto_angele;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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
    TextView usuario;
    TextView email;
    TextView descricao;
    TextView nome;
    ImageView perfil;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        usuario = findViewById(R.id.perfil_usuario);
        email = findViewById(R.id.perfil_email);
        descricao = findViewById(R.id.perfil_descricao);
        nome = findViewById(R.id.perfil_nome);
        perfil = findViewById(R.id.perfil_img);

        Intent intent = getIntent();
        usuario_principal = (Usuario) intent.getExtras().get(Usuario.USUARIO_INFO);

        usuario.setText(usuario_principal.getLogin());
        email.setText(usuario_principal.getEmail());
        nome.setText(usuario_principal.getNome());

        if (usuario_principal.getPerfil()!=null){
            perfil.setImageBitmap(usuario_principal.getPerfil());
        }


    }

    public void editar(View v){
        Intent editintent = new Intent(PerfilActivity.this, EditActivity.class);
        editintent.putExtra(Usuario.USUARIO_INFO, usuario_principal);
        startActivityForResult(editintent,1);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && data != null) {
            if (resultCode == RESULT_OK) {
                usuario_principal = (Usuario) data.getExtras().get("data");

                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                email.setText(usuario_principal.getEmail());
                nome.setText(usuario_principal.getNome());
                descricao.setText(usuario_principal.getDescricao());
                perfil.setImageBitmap(usuario_principal.getPerfil());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onResume() {

        super.onResume();
        this.onCreate(null);
    }
}
