/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jéssica, Breno
 */
public class Dados {
    
    public static List<Dado> parseDataFromJsonString(String jsonString) {
        List<Dado> listaDados = new ArrayList<>();
        
        
        JSONObject object = new JSONObject(jsonString);
        JSONArray data = object.getJSONArray("data");
        
        Dado dado;
        
        for (int i = 0; i < data.length(); i++) {
            JSONArray dados = data.getJSONArray(i);
            dado = new Dado();
            dado.setTipo(dados.getString(8));
            dado.setAno(dados.getInt(9));
            dado.setTaxa(dados.getDouble(10));
            listaDados.add(dado);
        }
        
        
        return listaDados;
    }
    
    public static String readFileFromUrl(URL url) throws IOException {
        InputStreamReader is = new InputStreamReader(url.openStream());
        String jsonString = getStringFromInputStream(is);
        return jsonString;
    }
    
    private static String getStringFromInputStream(InputStreamReader is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(is);
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
    
    public static List<Dado> getDataFromFile(File file) {
        List<Dado> dados = new ArrayList<>();
        ObjectInputStream reader = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(file));
            dados = (List<Dado>) reader.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        } finally { 
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return dados;
    }
    
    public static void saveDataInFile(List<Dado> dados, File file) {
        ObjectOutputStream writer = null;
        try {
            writer = new ObjectOutputStream(new FileOutputStream(file));
            writer.writeObject(dados);
        } catch (IOException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
