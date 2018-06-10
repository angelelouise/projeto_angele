package com.example.angelelouise.projeto_angele.api;

import com.example.angelelouise.projeto_angele.dominio.Usuario;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuarioService {
    @GET("/usuario")
    @Headers({
            "Accept: application/json"
    })
    Call<List<Usuario>> listar();

    @GET("usuario/list/{user}")
    @Headers({
            "Accept: application/json"
    })
    Call<Usuario> listarUser(@Path("user") String user);

    @POST("/usuario")
    @Headers({
            "Accept: application/json",
            "Content-type:application/json"
    })
    Call<Usuario> inserir(@Body Usuario usuario);

    @PUT("/usuario")
    @Headers({
            "Accept: application/json",
            "Content-type:application/json"
    })
    Call<Usuario> atualizar(@Body Usuario usuario);

    @DELETE("/usuario/{id}")
    @Headers({
            "Accept: application/json"
    })
    Call<Usuario> remover(@Path("id")Long id);



}
