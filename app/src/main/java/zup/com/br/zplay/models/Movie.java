package zup.com.br.zplay.models;

import java.io.Serializable;

public class Movie implements Serializable{

    private String nome;
    private String autor;
    private String ano;
    private String sinopse;
    private String tempo;
    private String image;
    private String elenco;
    private int rank;

    public Movie() {
    }

    public Movie(String nome, String autor, String ano, String sinopse, String tempo, String image, int rank) {
        this.nome = nome;
        this.autor = autor;
        this.ano = ano;
        this.sinopse = sinopse;
        this.tempo = tempo;
        this.image = image;
        this.rank = rank;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
