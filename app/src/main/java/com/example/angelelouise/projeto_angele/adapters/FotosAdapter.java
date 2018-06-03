package com.example.angelelouise.projeto_angele.adapters;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angelelouise.projeto_angele.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AngeleLouise on 05/05/2018.
 */

public class FotosAdapter extends BaseAdapter{
    private final Context mContext;
    private List<Bitmap> fotos;

    public FotosAdapter(Context mContext, List<Bitmap> fotos) {
        this.mContext = mContext;
        this.fotos=fotos;
    }

    @Override
    public int getCount() {
        return fotos.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null)
            row = LayoutInflater.from(mContext).inflate(R.layout.grid_personalizado, parent, false);

        Bitmap foto_atual = fotos.get(i);
            ImageView image = row.findViewById(R.id.foto);
            image.setImageBitmap(foto_atual);

        return row;
    }
}
