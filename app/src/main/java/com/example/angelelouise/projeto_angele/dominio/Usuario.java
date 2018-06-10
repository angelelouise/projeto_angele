package com.example.angelelouise.projeto_angele.dominio;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by AngeleLouise on 30/04/2018.
 */

public class Usuario implements Serializable {
    public static final String USUARIO_INFO = "USUARIO_INFO";
    private Long id;
    private String user;
    private String senha;
    private String email;
    private String nome;
    private String descricao;
    private Bitmap perfil;
    private Date dataCadastro;

    public Usuario(String user, String senha, String email, String nome, String descricao, Long id) {
        this.email= email;
        this.user = user;
        this.senha = senha;
        this.nome = nome;
        this.descricao=descricao;
        this.id=id;
    }


    @Override
    public String toString() {
        String saida = "user: %s, senha: %s, nome: %s, email: %s, descricao: %s, id: %s";
        return String.format(saida,user , senha, email, nome, descricao,id);
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

    public String getUser() {
        return user;
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

    public void setUser(String user) {
        this.user = user;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
