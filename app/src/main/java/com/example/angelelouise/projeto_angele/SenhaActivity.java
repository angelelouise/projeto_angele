package com.example.angelelouise.projeto_angele;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angelelouise.projeto_angele.dominio.Usuario;

/**
 * Created by AngeleLouise on 01/05/2018.
 * IMPLEMENTAÇÃO INCOMPLETA
 */

public class SenhaActivity extends Activity{
    private TextView senha_antiga;
    private TextView nova_senha1;
    private TextView nova_senha2;
    private Usuario usuario_principal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senha);

        senha_antiga = findViewById(R.id.senha_antiga);
        nova_senha1 = findViewById(R.id.nova_senha1);
        nova_senha2 = findViewById(R.id.nova_senha2);

        Intent intent = getIntent();
        usuario_principal = (Usuario) intent.getExtras().get(Usuario.USUARIO_INFO);
    }

    public void salvar_senha(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("A senha será atualizada, deseja prosseguir?").setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        validar_senhas();
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
    private void validar_senhas(){
        if(senha_antiga.equals(usuario_principal.getSenha())){
            if (nova_senha1.equals(nova_senha2)){
                usuario_principal.setSenha(nova_senha1.getText().toString());
            }
            else{
                Toast toast = Toast.makeText(SenhaActivity.this, "Repetir a nova senha!",1);
                toast.show();
                nova_senha1.setText("");
                nova_senha2.setText("");
            }
        }
        else{
            Toast toast = Toast.makeText(SenhaActivity.this, "Senha atual incorreta.",1);
            toast.show();
            senha_antiga.setText("");
        }
    }
    public void cancelar_senha(View v){
        finish();
    }

}
