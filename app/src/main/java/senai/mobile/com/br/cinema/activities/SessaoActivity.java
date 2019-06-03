package senai.mobile.com.br.cinema.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import senai.mobile.com.br.cinema.R;
import senai.mobile.com.br.cinema.model.Secao;
import senai.mobile.com.br.cinema.retrofit.RetrofitConfig;

public class SessaoActivity extends AppCompatActivity {

    Spinner sistemas;
    LinearLayout mainLayout;
    LinearLayout segundoLayout;
    private ListView listViewSecoes;
    private List<HashMap<String, String>> listSecoes = new ArrayList<>();
    private Button btnComprarIngresso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessao);

        listViewSecoes = (ListView) findViewById(R.id.listViewSecoes);
        btnComprarIngresso = (Button) findViewById(R.id.btnComprarIngresso);

        sistemas = (Spinner) findViewById(R.id.Lançamento);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Tela_de_seções, android.R.layout.simple_spinner_item);
        sistemas.setAdapter(adapter);

        sistemas = (Spinner) findViewById(R.id.Lançamentos);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.Tela_de_seções, android.R.layout.simple_spinner_item);
        sistemas.setAdapter(adapter);

        mainLayout = findViewById(R.id.main_layout);
        segundoLayout = findViewById(R.id.segundo_layout);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mainLayout.setOrientation(LinearLayout.HORIZONTAL);
        }
        else {
            mainLayout.setOrientation(LinearLayout.VERTICAL);
        }

        listarSessoes();

        //irParaTelaDePagamento();


    }

    public void listarSessoes() {

        Call<List<Secao>> call = new RetrofitConfig().getSessaoService().list();
        call.enqueue(new Callback<List<Secao>>() {

            @Override
            public void onResponse(Call<List<Secao>> call, Response<List<Secao>> response) {
                List<Secao> secoes = response.body();

                for (Secao secao : secoes) {
                    exibir(secao);
                }

            }

            @Override
            public void onFailure(Call<List<Secao>> call, Throwable t) {
                Log.e("SecaoService   ", "Erro ao buscar a Secao:" + t.getMessage());
            }

        });

    }

    public void exibir(Secao secao) {

        HashMap<String, String> m = new HashMap();

        m.put("filme", secao.getNomeDoFilme());
        m.put("data", secao.getData());
        m.put("sala", secao.getNumeroDaSala());
        listSecoes.add(m);

        String[] from={"filme" ,"data", "sala"};
        int[] to={R.id.tvFilmeSecao, R.id.tvDataSecao, R.id.tvSalaSecao};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listSecoes, R.layout.activity_list_view_secoes, from, to);
        listViewSecoes.setAdapter(simpleAdapter);

    }

    public void irParaTelaDePagamento() {

        this.btnComprarIngresso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SessaoActivity.this, PagamentoActivity.class);
                startActivity(intent);

            }
        });

    }


}
