package com.example.angelelouise.projeto_angele;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 * Created by AngeleLouise on 30/04/2018.
 */

public class LoginActivity extends Activity{
    private String user;
    private String password;
    private Usuario usuario_principal;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView usuario = findViewById(R.id.in_user);
        TextView senha = findViewById(R.id.in_password);

        SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
        user = settings.getString("user","");
        password = settings.getString("password","");

        usuario.setText(user);
    }

    public void logar(View view){
        TextView usuario = findViewById(R.id.in_user);
        TextView senha = findViewById(R.id.in_password);
        user= usuario.getText().toString();
        password = senha.getText().toString();
        usuario_principal= new Usuario(user, password, "mail@mail.com.br", user);

        SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("user",user);
        editor.putString("password",password);

        editor.commit();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
