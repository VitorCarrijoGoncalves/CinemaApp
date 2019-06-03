package senai.mobile.com.br.cinema.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import senai.mobile.com.br.cinema.dto.UsuarioDTO;

public interface UsuarioService {

    @POST("/users/auth") //auth
    Call<UsuarioDTO> postLogin(@Body UsuarioDTO usuarioDTO);

}
