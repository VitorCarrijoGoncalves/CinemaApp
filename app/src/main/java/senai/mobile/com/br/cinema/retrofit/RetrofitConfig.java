package senai.mobile.com.br.cinema.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import senai.mobile.com.br.cinema.service.FilmeService;
import senai.mobile.com.br.cinema.service.SessaoService;
import senai.mobile.com.br.cinema.service.UsuarioService;

public class RetrofitConfig {

    public final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.226:8090/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public FilmeService getFilmeService() {
        return this.retrofit.create(FilmeService.class);
    }

    public SessaoService getSessaoService() {
        return this.retrofit.create(SessaoService.class);
    }

    public UsuarioService getUsuarioService() {
        return this.retrofit.create(UsuarioService.class);
    }

}
