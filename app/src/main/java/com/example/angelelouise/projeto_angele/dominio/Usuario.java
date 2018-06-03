package com.example.angelelouise.projeto_angele.dominio;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by AngeleLouise on 30/04/2018.
 */

public class Usuario implements Serializable {
    public static final String USUARIO_INFO = "USUARIO_INFO";
    private String login;
    private String senha;
    private String email;
    private String nome;
    private String descricao;
    private Bitmap perfil;
    private Date dataCadastro;

    public Usuario(String login, String senha, String email, String nome, String descricao, Date dataCadastro) {
        this.email= email;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.descricao=descricao;
        this.dataCadastro=dataCadastro;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataCadastro='" + dataCadastro + '\'' +
                '}';
    }

    public Bitmap getPerfil() {
        return perfil;
    }

    public void setPerfil(Bitmap perfil) {
        this.perfil = perfil;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
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
