package com.example.angelelouise.projeto_angele;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by AngeleLouise on 30/04/2018.
 */

public class Usuario implements Serializable {
    private String login;
    private String senha;
    private String email;
    private String nome;
    private String descricao;
    private Bitmap perfil;

    public Usuario(String login, String senha, String email, String nome) {
        this.email= email;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public Bitmap getPerfil() {
        return perfil;
    }

    public void setPerfil(Bitmap perfil) {
        this.perfil = perfil;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
