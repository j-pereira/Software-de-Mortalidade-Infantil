/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dados;

import static com.dados.Dados.getDataFromFile;
import static com.dados.Dados.readFileFromUrl;
import static com.dados.Dados.saveDataInFile;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jéssica
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            File file = new File("dados.dat");
            
            List<Dado> dados = new ArrayList<>();
            
            if (file.exists() && !file.isDirectory()) {
                dados = getDataFromFile(file);
            } else {
                URL url = new URL("https://data.cdc.gov/api/views/epev-k6ss/rows.json?accessType=DOWNLOAD");
                String jsonString = readFileFromUrl(url);
                dados = Dados.parseDataFromJsonString(jsonString);
                saveDataInFile(dados, file);
            }
            
            for (int i = 0; i < dados.size(); i++) {
                Dado dado = dados.get(i);
                System.out.print("\nTipo: " + dado.getTipo());
                System.out.print("\nAno: " + dado.getAno());
                System.out.print("\nTaxa: " + dado.getTaxa());
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
}
