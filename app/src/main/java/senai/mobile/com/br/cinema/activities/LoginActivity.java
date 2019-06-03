package senai.mobile.com.br.cinema.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import senai.mobile.com.br.cinema.R;
import senai.mobile.com.br.cinema.dto.UsuarioDTO;
import senai.mobile.com.br.cinema.retrofit.RetrofitConfig;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmailLogin;
    private EditText edtSenhaLogin;
    private Button btnLogin;
    private TextView tvLinkCadastroDeUsuario;
    private UsuarioDTO usuarioDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmailLogin = findViewById(R.id.edtEmail);
        edtSenhaLogin = findViewById(R.id.edtESenha);
        btnLogin = findViewById(R.id.btnLogin);
        tvLinkCadastroDeUsuario = findViewById(R.id.tvLinkCadastroDeUsuario);

        tvLinkCadastroDeUsuario.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isLoginValido()) {

                    usuarioDTO = new UsuarioDTO();

                    usuarioDTO.setEmail(edtEmailLogin.getText().toString());
                    usuarioDTO.setSenha(edtSenhaLogin.getText().toString());

                    enviarPost(usuarioDTO);

                } else {
                    Toast.makeText(LoginActivity.this, "Preencha os campos de E-mail e Senha", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private boolean isLoginValido() {
        return !edtEmailLogin.getText().toString().equalsIgnoreCase("") || !edtSenhaLogin.getText().toString().equalsIgnoreCase("");
    }

    private void abrirTelaHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void enviarPost(UsuarioDTO usuarioDTO) {

        Call<UsuarioDTO> call = new RetrofitConfig().getUsuarioService().postLogin(usuarioDTO);
        call.enqueue(new Callback<UsuarioDTO>() {
            @Override
            public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {

                if(response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Usuário logado com sucesso", Toast.LENGTH_LONG).show();
                    abrirTelaHome();
                }

            }

            @Override
            public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao cadastrar", Toast.LENGTH_LONG).show();
            }

        });
    }


}
