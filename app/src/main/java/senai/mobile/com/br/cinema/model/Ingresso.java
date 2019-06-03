package senai.mobile.com.br.cinema.model;

public class Ingresso {

    private Integer id;

    private Usuario idUsuario;

    private Secao idSessao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Secao getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Secao idSessao) {
        this.idSessao = idSessao;
    }

    @Override
    public String toString() {
        return "Ingresso{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", idSessao=" + idSessao +
                '}';
    }
}