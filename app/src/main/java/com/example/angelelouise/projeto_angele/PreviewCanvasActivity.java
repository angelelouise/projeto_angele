package com.example.angelelouise.projeto_angele;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

/**
 * Created by AngeleLouise on 05/05/2018.
 */

public class PreviewCanvasActivity extends Activity{
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        ImageView preview = findViewById(R.id.preview);
        Intent intent = getIntent();
        bitmap = (Bitmap) intent.getExtras().get("imagem");
        preview.setImageBitmap(bitmap);
    }
}
