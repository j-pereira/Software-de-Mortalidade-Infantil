package com.dados;

/**
 *
 * @author Jéssica
 */

public class Dados {
    private String tipo;
    private int ano;
    private float taxa;
    
    
    public Dados(){
    
    }
    
    public Dados(String tipo, int ano, float taxa){
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

    public float getTaxa() {
        return taxa;
    }

    public void setTaxa(float taxa) {
        this.taxa = taxa;
    }
    
}
