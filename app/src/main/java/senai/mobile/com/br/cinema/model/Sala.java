package senai.mobile.com.br.cinema.model;

import java.util.List;

public class Sala {

    private Integer id;

    private String numero;

    private long quantidadeDeLugares;

    private List<Secao> sessoes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public long getQuantidadeDeLugares() {
        return quantidadeDeLugares;
    }

    public void setQuantidadeDeLugares(long quantidadeDeLugares) {
        this.quantidadeDeLugares = quantidadeDeLugares;
    }

    public List<Secao> getSessoes() {
        return sessoes;
    }

    public void setSessoes(List<Secao> sessoes) {
        this.sessoes = sessoes;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", quantidadeDeLugares=" + quantidadeDeLugares +
                ", sessoes=" + sessoes +
                '}';
    }
}