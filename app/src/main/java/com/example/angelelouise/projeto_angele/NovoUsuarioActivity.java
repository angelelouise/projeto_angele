package com.example.angelelouise.projeto_angele;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.angelelouise.projeto_angele.api.UsuarioService;
import com.example.angelelouise.projeto_angele.dominio.Usuario;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NovoUsuarioActivity extends Activity{
    TextView usuario;
    TextView senha;
    TextView email;
    TextView nome;
    static String baseUrl = "https://angproj-service.herokuapp.com/";
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_novousuario);
        usuario = findViewById(R.id.uUser);
        senha=findViewById(R.id.uSenha);
        email=findViewById(R.id.uEmail);
        nome=findViewById(R.id.uNome);
    }

    public void inserir(View v) throws Exception{

        final Usuario u = new Usuario(usuario.toString(),senha.toString(),email.toString(),nome.toString(), "", new Date());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsuarioService service = retrofit.create(UsuarioService.class);

        Call<Usuario> callUsuario = service.inserir(u);

        callUsuario.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario Usuario = response.body();

                Toast.makeText(NovoUsuarioActivity.this,
                        "Usuario cadastrado com sucesso",
                        Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e(this.getClass().getName(), "ERRO",t);
            }
        });
    }

}
