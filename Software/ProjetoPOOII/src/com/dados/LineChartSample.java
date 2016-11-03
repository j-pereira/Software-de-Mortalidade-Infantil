/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dados;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class LineChartSample extends Application {
 
    @Override public void start(Stage stage) {
        stage.setTitle("Gráfico");
        //definindo os eixos
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Ano");
        yAxis.setLabel("Taxa");
        //criando o gráfico
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
        
        //definindo o título
        lineChart.setTitle("Gráfico do resultado da pesquisa");
        
        //defining a series
        XYChart.Series series = new XYChart.Series();
        
        //para setar o tipo 
        String tipo="Infantil";
        series.setName(tipo);
        
        //para colocar as informações nos pontos
        int pos;
        int vetor[] = new int[10]; 
        for(pos=0;pos<10;pos++){
            vetor[pos]=pos*5;
        }
        pos=0;
        for(int i=0;i<10;i++){
            series.getData().add(new XYChart.Data(i, vetor[pos]));
            pos = pos+1;
        }
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}