package senai.mobile.com.br.cinema.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import senai.mobile.com.br.cinema.dto.UsuarioDTO;
import senai.mobile.com.br.cinema.model.Usuario;

public interface UsuarioService {

    @POST("/users/auth") //auth
    Call<UsuarioDTO> postLogin(@Body UsuarioDTO usuarioDTO);

    @POST("/users/new") //new-user
    Call<Usuario> postCadastroDeUsuario(@Body Usuario usuario);

}
