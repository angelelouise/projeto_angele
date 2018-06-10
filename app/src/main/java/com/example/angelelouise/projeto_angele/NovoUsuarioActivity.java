package com.example.angelelouise.projeto_angele;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.angelelouise.projeto_angele.api.UsuarioService;
import com.example.angelelouise.projeto_angele.dominio.Usuario;

import java.util.List;

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
    boolean ok =false;
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

        final Usuario u = new Usuario(usuario.getText().toString(),senha.getText().toString(),email.getText().toString(),nome.getText().toString(), "", (long) 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final UsuarioService service = retrofit.create(UsuarioService.class);

        final Call<List<Usuario>> listaUsuarios = service.listar();
        listaUsuarios.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                Log.d("Response", response.body().toString());
                assert response.body() != null;
                List<Usuario> usuariosList= response.body();
                for(int i =0; i <=response.body().size()-1; i++){
                    if (usuariosList.get(i).getUser().equals(u.getUser()) || usuariosList.get(i).getEmail().equals(u.getEmail())){
                        Toast.makeText(NovoUsuarioActivity.this,
                                "Já existe um usuário cadastrado com esses dados" +usuariosList.get(i),
                                Toast.LENGTH_SHORT).show();
                    }else{
                        ok=true;
                    }
                }
                if(ok){
                    Call<Usuario> callUsuario = service.inserir(u);

                    callUsuario.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            assert response.body() != null;
                            Usuario Usuario = response.body();

                            Toast.makeText(NovoUsuarioActivity.this,
                                    "Usuario cadastrado com sucesso"+ Usuario,
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

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e(NovoUsuarioActivity.class.getName(), "ERRO", t);
            }
        });




    }

}
