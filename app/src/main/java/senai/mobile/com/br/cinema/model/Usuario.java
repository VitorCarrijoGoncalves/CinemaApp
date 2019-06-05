package senai.mobile.com.br.cinema.model;

import java.util.List;

public class Usuario {

    private Integer id;

    private String senha;

    private String nome;

    private String email;

    private String idade;

    private String sexo;

    private String estadoCivil;

    private List<Ingresso> ingressos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", senha='" + senha + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", idade='" + idade + '\'' +
                ", sexo='" + sexo + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", ingressos=" + ingressos +
                '}';
    }
}