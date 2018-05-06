package com.example.angelelouise.projeto_angele;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.angelelouise.projeto_angele.RWPerfil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AngeleLouise on 30/04/2018.
 */

public class MainActivity extends Activity {
    protected static final int PERFIL = 1;
    protected static final int CANVAS = 0;
    private Usuario usuario_principal;
    private FotosAdapter fotosAdapter;
    private RWPerfil rw;
    ImageView perfil;
    TextView descricao;
    TextView email;
    TextView nome;
    List<Bitmap> canvas = new ArrayList<>();
    GridView canvas1;
    Bitmap bit;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.nome);
        email = findViewById(R.id.email);
        descricao = findViewById(R.id.descricao);
        perfil = findViewById(R.id.perfil_img);
        canvas1 = findViewById(R.id.canvas);

        if(savedInstanceState != null){
            canvas = (ArrayList<Bitmap>) savedInstanceState.getSerializable("fotos");
        }
        fotosAdapter = new FotosAdapter(MainActivity.this, canvas);
        canvas1.setAdapter(fotosAdapter);

        Intent intent = getIntent();
        usuario_principal = (Usuario) intent.getExtras().get(Usuario.USUARIO_INFO);

        nome.setText(usuario_principal.getNome());
        email.setText(usuario_principal.getEmail());
        descricao.setText(usuario_principal.getDescricao());

        setImage(perfil);
        canvas1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bitmap imagemselecionada = canvas.get(i);

                Intent intent = new Intent(MainActivity.this, PreviewCanvas.class);
                intent.putExtra( "imagem", imagemselecionada);
                startActivity(intent);

            }
        });

    }
    protected void onSaveInstanceState (Bundle outState ){
        super.onSaveInstanceState(outState);
        outState.putSerializable("fotos", (Serializable) canvas);
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
                perfil.putExtra(Usuario.USUARIO_INFO, usuario_principal);
                startActivityForResult(perfil, PERFIL);
                return true;
            case R.id.Logout:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void tirar_foto (View v){
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(camera, CANVAS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CANVAS:
                if (resultCode == RESULT_OK) {
                    bit = (Bitmap) data.getExtras().get("data");
                    salvar_memoria_interna(bit);
                    canvas.add(0, bit);
                    fotosAdapter.notifyDataSetChanged();
                }
                return;
            case PERFIL:
                Usuario usuario_secundario = (Usuario) data.getSerializableExtra(Usuario.USUARIO_INFO);
                usuario_principal = usuario_secundario;

                email.setText(usuario_principal.getEmail());
                nome.setText(usuario_principal.getNome());
                descricao.setText(usuario_principal.getDescricao());
        }
    }

    private void salvar_memoria_interna(Bitmap bitmapImage){
        String state = Environment.getExternalStorageState();
        int permission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    PERMISSIONS_STORAGE,
                    1
            );
        }
        if (Environment.MEDIA_MOUNTED.equals(state)){
            File filename;
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            int n=0;
            n++;
            try {

                filename = new File(folder,"app/image"+n+".jpg");
                filename.getParentFile().mkdirs();
                filename.createNewFile();
                
                FileOutputStream out = new FileOutputStream(filename);

                bitmapImage.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

                MediaStore.Images.Media.insertImage(getContentResolver(), filename.getAbsolutePath(), filename.getName(), filename.getName());

                Toast.makeText(getApplicationContext(), "File is Saved in  " + filename, Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
    @Override
    protected void onResume() {
        email.setText(usuario_principal.getEmail());
        nome.setText(usuario_principal.getNome());
        descricao.setText(usuario_principal.getDescricao());
        setImage(perfil);
        super.onResume();
    }
}
