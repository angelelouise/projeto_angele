package com.example.angelelouise.projeto_angele.api;

import com.example.angelelouise.projeto_angele.dominio.Usuario;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuarioService {
    @GET("/usuario")
    @Headers({
            "Accept: application/json"
    })
    Call<List<Usuario>> listar();


    @POST("/usuario")
    @Headers({
            "Accept: application/json",
            "Content-type:application/json"
    })
    Call<Usuario> inserir(@Body Usuario usuario);
}
