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
    private String usuario;
    private String senha;
    private String email;
    private String nome;
    private String descricao;
    private Bitmap perfil;
    private Date dataCadastro;

    public Usuario(String usuario, String senha, String email, String nome, String descricao) {
        this.email= email;
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
        this.descricao=descricao;
    }


    @Override
    public String toString() {
        String saida = "user: %s, senha: %s, nome: %s, email: %s, descricao: %s";
        return String.format(saida,usuario , senha, email, nome, descricao);
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
        return usuario;
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

    public void setUser(String usuario) {
        this.usuario = usuario;
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
