package senai.mobile.com.br.cinema.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import senai.mobile.com.br.cinema.model.Secao;

public interface SessaoService {

    @GET("/secoes/{id}")
    Call<Secao> getAlbum(@Path("id") String id);

    @GET("/secoes")
    Call<List<Secao>> list();

}
