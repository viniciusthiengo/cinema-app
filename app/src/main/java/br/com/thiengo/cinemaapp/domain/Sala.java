package br.com.thiengo.cinemaapp.domain;

import java.util.ArrayList;

/**
 * Created by viniciusthiengo on 03/03/17.
 */

public class Sala {
    private int numero;
    private ArrayList<String> horarios = new ArrayList<>();

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<String> horarios) {
        this.horarios = horarios;
    }
}
