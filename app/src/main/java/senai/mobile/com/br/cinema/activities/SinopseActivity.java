package senai.mobile.com.br.cinema.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import senai.mobile.com.br.cinema.R;
import senai.mobile.com.br.cinema.model.Filme;
import senai.mobile.com.br.cinema.retrofit.RetrofitConfig;

public class SinopseActivity extends AppCompatActivity {

    private ListView listViewDetalhesDoFilme;
    private List<HashMap<String, String>> listFilmes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinopse);

        listViewDetalhesDoFilme = (ListView) findViewById(R.id.listViewDetalhesDoFilme);

    }

    public void listarFilmes() {

        Call<List<Filme>> call = new RetrofitConfig().getFilmeService().list();
        call.enqueue(new Callback<List<Filme>>() {

            @Override
            public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
                List<Filme> listFilmes = response.body();

                for (Filme filme : listFilmes) {
                    exibir(filme);
                }

            }

            @Override
            public void onFailure(Call<List<Filme>> call, Throwable t) {
                Log.e("FilmeService   ", "Erro ao buscar o Filme:" + t.getMessage());
            }

        });
    }

    public void exibir(Filme filme) {

        HashMap<String, String> m = new HashMap();

        m.put("Sinopse", filme.getSinopse());
        m.put("Gênero", filme.getGenero());
        m.put("Duração", filme.getDuracao());
        listFilmes.add(m);

        String[] from={"Sinopse", "Gênero", "Duração"};
        int[] to={R.id.tvNomeFilme, R.id.tvGeneroFilme, R.id.tvDuracaoFilme};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listFilmes, R.layout.activity_listar_filmes, from, to);
        listViewDetalhesDoFilme.setAdapter(simpleAdapter);

    }

}
