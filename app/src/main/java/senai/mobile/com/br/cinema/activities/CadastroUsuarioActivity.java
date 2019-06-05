package senai.mobile.com.br.cinema.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import senai.mobile.com.br.cinema.R;
import senai.mobile.com.br.cinema.model.Usuario;
import senai.mobile.com.br.cinema.retrofit.RetrofitConfig;

public class CadastroUsuarioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText nome;
    private EditText email;
    private EditText senha1;
    private EditText senha2;
    private EditText idade;
    private Spinner sexo;
    private Spinner estadoCivil;
    private Button btnCadastrar;
    private Button btnCancelar;
    private Usuario usuario;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        nome = findViewById(R.id.edtCadNome);
        email = findViewById(R.id.edtCadEmail);
        senha1 = findViewById(R.id.edtCadSenha1);
        senha2 = findViewById(R.id.edtCadSenha2);
        idade = findViewById(R.id.edtCadIdade);
        sexo = findViewById(R.id.spinnerSexo);
        estadoCivil = findViewById(R.id.spinnerEstadoCivil);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCancelar = findViewById(R.id.btnCancelar);

        ArrayAdapter<CharSequence> adapterSexo = ArrayAdapter.createFromResource(this, R.array.sexo, android.R.layout.simple_spinner_item);
        adapterSexo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexo.setAdapter(adapterSexo);
        sexo.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        ArrayAdapter<CharSequence> adapterEC = ArrayAdapter.createFromResource(this, R.array.estadoCivil, android.R.layout.simple_spinner_item);
        adapterEC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoCivil.setAdapter(adapterEC);
        estadoCivil.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (senha1.getText().toString().equals(senha2.getText().toString())) {

                    usuario = new Usuario();

                    usuario.setNome(nome.getText().toString());
                    usuario.setEmail(email.getText().toString());
                    usuario.setSenha(senha1.getText().toString());
                    usuario.setIdade(idade.getText().toString());
                    usuario.setSexo(sexo.getSelectedItem().toString());
                    usuario.setEstadoCivil(estadoCivil.getSelectedItem().toString());

                    // chamada de método para cadastro de Usuários
                    cadastrarUsuario(usuario);

                } else {
                    Toast.makeText(CadastroUsuarioActivity.this, "As senhas não se correspondem", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroUsuarioActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void fazerLogin() {

        Intent intent = new Intent(CadastroUsuarioActivity.this, LoginActivity.class);
        startActivity(intent);

    }

    public void cadastrarUsuario(Usuario usuario) {

        if (validarUsuario(usuario)) {

            Call<Usuario> call = new RetrofitConfig().getUsuarioService().postCadastroDeUsuario(usuario);
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                    if(response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Usuário Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                        fazerLogin();
                    }

                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro ao cadastrar", Toast.LENGTH_LONG).show();
                }

            });

        }

    }

    private boolean validarUsuario(Usuario usuario) {

        if (usuario.getNome().trim().equals("") ||
                usuario.getEmail().trim().equals("") ||
        usuario.getIdade().trim().equals("") ||
        usuario.getSexo().trim().equals("") ||
        usuario.getEstadoCivil().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
