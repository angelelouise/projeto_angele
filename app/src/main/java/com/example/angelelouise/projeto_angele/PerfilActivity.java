package com.example.angelelouise.projeto_angele;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angelelouise.projeto_angele.dominio.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


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
    Bitmap bitmap;
    private boolean atualizado=false;
    protected static final int EDITAR = 0;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        usuario = findViewById(R.id.perfil_usuario);
        email = findViewById(R.id.perfil_email);
        descricao = findViewById(R.id.perfil_descricao);
        nome = findViewById(R.id.perfil_nome);
        perfil = findViewById(R.id.perfil_img);

        if(!atualizado) {
            Intent intent = getIntent();
            usuario_principal = (Usuario) intent.getExtras().get(Usuario.USUARIO_INFO);
        }

        usuario.setText(usuario_principal.getLogin());
        email.setText(usuario_principal.getEmail());
        nome.setText(usuario_principal.getNome());

        //if (usuario_principal.getPerfil()!=null){
            //perfil.setImageBitmap(usuario_principal.getPerfil());
        //}else {
            //perfil.setImageResource(R.drawable.a);
        //}
        setImage(perfil);

    }

    public void editar(View v){
        Intent editintent = new Intent(PerfilActivity.this, EditActivity.class);
        editintent.putExtra(Usuario.USUARIO_INFO, usuario_principal);
        startActivityForResult(editintent, EDITAR);
    }
    public void voltar(View v){
        final Intent resultado = new Intent();
        resultado.putExtra(Usuario.USUARIO_INFO, usuario_principal);
        setResult(RESULT_OK, resultado);
        finish();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == EDITAR && data != null) {
            if (resultCode == RESULT_OK) {
                atualizado=true;
                Usuario usuario_secundario = (Usuario) data.getSerializableExtra(Usuario.USUARIO_INFO);
                usuario_principal = usuario_secundario;

                email.setText(usuario_principal.getEmail());
                nome.setText(usuario_principal.getNome());
                descricao.setText(usuario_principal.getDescricao());

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onResume() {
        email.setText(usuario_principal.getEmail());
        nome.setText(usuario_principal.getNome());
        descricao.setText(usuario_principal.getDescricao());
        setImage(perfil);
        super.onResume();
        this.onCreate(null);
    }

    private void setImage(ImageView perfil)
    {

        try {
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File f=new File(folder, "app/perfil.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img= findViewById(R.id.perfil_img);
            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            perfil.setImageResource(R.drawable.a);
            e.printStackTrace();
        }

    }


}
