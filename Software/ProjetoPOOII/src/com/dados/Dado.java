package com.dados;


import java.io.Serializable;

/**
 *
 * @author Matheus
 */
public class Dado implements Serializable {
    private String tipo;
    private int ano;
    private double taxa;
    
    
    public Dado(){
    
    }
    
    public Dado(String tipo, int ano, float taxa){
        this.tipo = tipo;
        this.ano = ano;
        this.taxa = taxa;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }
    
}
