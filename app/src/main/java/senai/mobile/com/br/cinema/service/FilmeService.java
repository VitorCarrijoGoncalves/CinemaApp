package senai.mobile.com.br.cinema.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import senai.mobile.com.br.cinema.dto.FilmeDTO;
import senai.mobile.com.br.cinema.model.Filme;

public interface FilmeService {

    @GET("/filmes/{id}")
    Call<Filme> getFilme(@Path("id") String id);

    @GET("/filmes")
    Call<List<Filme>> list();

}
