package com.example.angelelouise.projeto_angele.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.example.angelelouise.projeto_angele.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by AngeleLouise on 05/05/2018.
 */

public class RWPerfil {

private ImageView perfil;
private Bitmap bitmap;

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

    public void getImage(Bitmap bitmap){
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

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}