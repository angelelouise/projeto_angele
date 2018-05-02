package com.example.angelelouise.projeto_angele;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by AngeleLouise on 30/04/2018.
 */

public class EditActivity extends Activity{
    private Usuario usuario_principal;
    private TextView usuario;
    private TextView email;
    private TextView descricao;
    private TextView nome;
    private ImageView perfil;
    protected static final int FOTO = 1;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        usuario = findViewById(R.id.perfil_usuario);
        email = findViewById(R.id.perfil_email);
        descricao = findViewById(R.id.perfil_descricao);
        nome = findViewById(R.id.perfil_nome);
        perfil = findViewById(R.id.perfil_img);

        usuario.setText(usuario_principal.getLogin());
        email.setText(usuario_principal.getEmail());
        nome.setText(usuario_principal.getNome());

    }

    public void trocar_senha (View v){
        Intent senhaintent =  new Intent(EditActivity.this, SenhaActivity.class);
        startActivity(senhaintent);
    }

    public void salvar (View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("O tweet será atualizado, deseja prosseguir?").setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        usuario_principal.setNome(nome.getText().toString());
                        usuario_principal.setEmail(email.getText().toString());
                        usuario_principal.setDescricao(descricao.getText().toString());
                        finish();
                    }
                }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public void cancelar (View v){
        finish();
    }

    public void trocar_foto (View v){
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(camera, FOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (equals(FOTO)){
            if (resultCode == RESULT_OK) {
                bitmap = (Bitmap) data.getExtras().get("data");
                perfil.setImageBitmap(bitmap);
                usuario_principal.setPerfil(bitmap);
            }
        }
    }
}
