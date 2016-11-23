/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dados;


import static com.dados.Dados.getDataFromFile;
import static com.dados.Dados.readFileFromUrl;
import static com.dados.Dados.saveDataInFile;
import com.telas.TelaMain;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class LineChartSample {
    
    public static String tipo;
    public static int anoInicio;
    public static int anoFinal;
    public static int taxa;

    public LineChart<Number, Number> getChart() {
        final NumberAxis xAxis = new NumberAxis((anoInicio-1),(anoFinal+1),1);
        xAxis.setLowerBound(anoInicio-1);
        xAxis.setUpperBound(anoFinal+1);
        final NumberAxis yAxis = new NumberAxis(0,110,1);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(110);
        
        
        xAxis.setLabel("Ano");
        yAxis.setLabel("Taxa");
         
         
         
        final LineChart<Number,Number> lineChart;
        lineChart = new LineChart<>(xAxis,yAxis);
       
        lineChart.setTitle("MORTALIDADE INFANTIL");
                          
        XYChart.Series series1 = new XYChart.Series();
        series1.setName(tipo);
        
        try{
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
         
           
            for(int i=0; i<dados.size(); i++){  
                if((dados.get(i).getTipo().equals(tipo))){
                    if((dados.get(i).getAno()>=anoInicio)&&(dados.get(i).getAno()<=anoFinal)){
                        if((dados.get(i).getTaxa()>=taxa)){
                            series1.getData().add(new XYChart.Data((int)dados.get(i).getAno(),dados.get(i).getTaxa()));//taxa , ano 
                        }
                    }
                }
            }
        }catch (MalformedURLException ex) {
            Logger.getLogger(TelaMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TelaMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lineChart.getData().addAll(series1);
        return lineChart;
    }
     public void Recebe(String Tipo, int AnoInicio, int AnoFinal, int Taxa){
        
        this.tipo = Tipo;
        this.anoInicio = AnoInicio;
        this.anoFinal = AnoFinal;
        this.taxa = Taxa;
        
        
    }
}