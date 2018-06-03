package com.example.angelelouise.projeto_angele;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angelelouise.projeto_angele.dominio.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;


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
    private Bitmap bitmap;
    protected static final int EDITAR_SENHA = 0;
    protected static final int FOTO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        usuario = findViewById(R.id.edit_usuario);
        email = findViewById(R.id.edit_email);
        descricao = findViewById(R.id.edit_descricao);
        nome = findViewById(R.id.edit_nome);
        perfil = findViewById(R.id.edit_img);

        Intent intent = getIntent();
        usuario_principal = (Usuario) intent.getExtras().get(Usuario.USUARIO_INFO);

        usuario.setText(usuario_principal.getLogin());
        email.setText(usuario_principal.getEmail());
        nome.setText(usuario_principal.getNome());

        setImage(perfil);
    }
    protected void onSaveInstanceState (Bundle outState ){
        super.onSaveInstanceState(outState);
        //identifico o que eu quero salvar
        outState.putSerializable("fotos", (Serializable) usuario_principal);
    }
    public void trocar_senha (View v){
        Intent senhaintent =  new Intent(EditActivity.this, SenhaActivity.class);
        senhaintent.putExtra(Usuario.USUARIO_INFO, usuario_principal);
        startActivityForResult(senhaintent, EDITAR_SENHA);
    }

    public void salvar (View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("O perfil será atualizado, deseja prosseguir?").setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        usuario_principal.setNome(nome.getText().toString());
                        usuario_principal.setEmail(email.getText().toString());
                        usuario_principal.setDescricao(descricao.getText().toString());

                        final Intent resultado = new Intent();
                        resultado.putExtra(Usuario.USUARIO_INFO, usuario_principal);
                        setResult(RESULT_OK, resultado);

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

        if (requestCode == FOTO && data != null) {
                if (resultCode == RESULT_OK) {
                    //bitmap = (Bitmap) data.getExtras().get("data");
                    Bundle extras = data.getExtras();
                    Bitmap bitmap = (Bitmap) extras.get("data");
                    perfil.setImageBitmap(bitmap);
                    String state = Environment.getExternalStorageState();
                    if (Environment.MEDIA_MOUNTED.equals(state)){
                        File filename;
                        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                        try {

                            filename = new File(folder,"app/perfil.jpg");
                            filename.getParentFile().mkdirs();

                            FileOutputStream out = new FileOutputStream(filename);

                            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                            out.flush();
                            out.close();

                            MediaStore.Images.Media.insertImage(getContentResolver(), filename.getAbsolutePath(), filename.getName(), filename.getName());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }

        }
        if (requestCode == EDITAR_SENHA && data != null) {
            Usuario usuario_secundario = (Usuario) data.getSerializableExtra(Usuario.USUARIO_INFO);
            usuario_principal = usuario_secundario;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void setImage(ImageView perfil) {

        try {
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File f = new File(folder, "app/perfil.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            perfil.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            perfil.setImageResource(R.drawable.a);
            e.printStackTrace();
        }
    }
}
