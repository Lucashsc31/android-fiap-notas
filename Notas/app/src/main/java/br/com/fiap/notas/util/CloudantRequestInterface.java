package br.com.fiap.notas.util;

import br.com.fiap.notas.entity.Doc;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CloudantRequestInterface {

    @GET("_all_docs?include_docs=true")
    Call<CloudantResponseNota> getAllJSON();

    @POST("fiap-notas")
    Call<Doc> cadastraNota(@Body Doc doc);
}
