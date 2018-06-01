package com.example.angelelouise.projeto_angele;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

import static com.example.angelelouise.projeto_angele.R.drawable.a;

/**
 * Created by AngeleLouise on 30/04/2018.
 */

public class LoginActivity extends Activity{
    private String user;
    private String password;
    private Usuario usuario_principal;
    TextView usuario;
    TextView senha;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = findViewById(R.id.in_user);
        senha = findViewById(R.id.in_password);

        SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
        user = settings.getString("user","");
        password = settings.getString("password","");

        usuario.setText(user);
    }

    public void logar(View view){

        user= usuario.getText().toString();
        password = senha.getText().toString();

        SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("user",user);
        editor.putString("password",password);

        editor.commit();

        if (popular_dados(user,password)){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra(Usuario.USUARIO_INFO, usuario_principal);
            startActivity(intent);
        }
    }

    private boolean popular_dados(String user, String password){
        usuario_principal= new Usuario(user, password, "mail@mail.com.br", user);
        usuario_principal.setDescricao(" ");
        return true;
    }
}
