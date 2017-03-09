package br.com.thiengo.cinemaapp.domain;

import java.util.ArrayList;

/**
 * Created by viniciusthiengo on 03/03/17.
 */

public class Filme {
    private String nome;
    private String urlImagem;
    private int numSalas;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public int getNumSalas() {
        return numSalas;
    }

    public void setNumSalas(int numSalas) {
        this.numSalas = numSalas;
    }
}
