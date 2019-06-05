package senai.mobile.com.br.cinema.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import senai.mobile.com.br.cinema.R;
import senai.mobile.com.br.cinema.adapters.AdapterListaFilmes;
import senai.mobile.com.br.cinema.model.Filme;
import senai.mobile.com.br.cinema.retrofit.RetrofitConfig;

public class HomeActivity extends AppCompatActivity {

    private ListView listViewFilmes;
    //private List<HashMap<String, String>> listFilmes = new ArrayList<>();
    private Button btnTelaSinopse;


    private List<Filme> listFilmes;
    private AdapterListaFilmes adapterListaFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listViewFilmes = findViewById(R.id.listViewFilmes);
        btnTelaSinopse = findViewById(R.id.btnTelaSinopse);

        listarFilmes();

        this.adapterListaFilmes = new AdapterListaFilmes(HomeActivity.this, this.listFilmes);

        this.listViewFilmes.setAdapter(this.adapterListaFilmes);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_sessao) {
            abrirTelaDeSessao();
        } else if (id == R.id.action_sair) {
            deslogarUsuario();
        }

        return super.onOptionsItemSelected(item);

    }

    private void abrirTelaDeSessao() {
        Intent intent = new Intent(HomeActivity.this, SessaoActivity.class);
        startActivity(intent);
}

    private void deslogarUsuario() {

        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);

    }

//    public void listarFilmes() {
//
//        Call<List<Filme>> call = new RetrofitConfig().getFilmeService().list();
//        call.enqueue(new Callback<List<Filme>>() {
//
//            @Override
//            public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
//                List<Filme> listFilmes = response.body();
//
//                for (Filme filme : listFilmes) {
//                    exibir(filme);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Filme>> call, Throwable t) {
//                Log.e("FilmeService   ", "Erro ao buscar o Filme:" + t.getMessage());
//            }
//
//        });
//    }

    public void listarFilmes() {

        Call<List<Filme>> call = new RetrofitConfig().getFilmeService().list();
        call.enqueue(new Callback<List<Filme>>() {

            @Override
            public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
                listFilmes = response.body();
            }

            @Override
            public void onFailure(Call<List<Filme>> call, Throwable t) {
                Log.e("FilmeService   ", "Erro ao buscar o Filme:" + t.getMessage());
            }

        });
    }

//    public void exibir(Filme filme) {
//
//        HashMap<String, String> m = new HashMap();
//
//        m.put("nome", filme.getNome());
//        listFilmes.add(m);
//
//        String[] from={"nome"};
//        int[] to={R.id.tvNomeFilme};
//
//        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listFilmes, R.layout.activity_listar_filmes, from, to);
//        listViewFilmes.setAdapter(simpleAdapter);
//
//    }

    public void irParaTelaDeSinopse() {

        this.btnTelaSinopse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, SinopseActivity.class);
                startActivity(intent);

            }
        });

    }


}
