package com.example.angelelouise.projeto_angele;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angelelouise.projeto_angele.api.UsuarioService;
import com.example.angelelouise.projeto_angele.dominio.Usuario;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NovoUsuarioActivity.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final UsuarioService service = retrofit.create(UsuarioService.class);

        final Call<Usuario> listarUsuario = service.listarUser(user); //retorna o usuário do servico

        listarUsuario.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                //verifica se a senha foi digitada corretamente
                if (response.body().getSenha().equals(password)){
                    SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("user",user);
                    editor.putString("password",password);

                    editor.commit();

                    if (popular_dados(user,password, response.body().getEmail(),response.body().getNome(),response.body().getId())){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra(Usuario.USUARIO_INFO, usuario_principal);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(LoginActivity.this,
                            "Senha ou usuário incorretos",
                            Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(LoginActivity.this,
                        "Usuario não existe",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void cadastrarUsuario (View v){
        Intent intent = new Intent(LoginActivity.this, NovoUsuarioActivity.class);
        startActivity(intent);
    }

    private boolean popular_dados(String user, String password, String email, String nome, Long id){
        usuario_principal= new Usuario(user, password, email, nome, "");
        usuario_principal.setId(id);
        return true;
    }
}
