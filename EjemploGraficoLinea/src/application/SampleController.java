package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class SampleController implements Initializable{
	private ObservableList<Data<String, Double>> ventas2016;
	private ObservableList<Data<String, Double>> ventas2015;
	private ObservableList<PieChart.Data> productos;
	
	private XYChart.Series<String, Double> serie1;
	private XYChart.Series<String, Double> serie2;
	
	@FXML private LineChart<String, Double> graficoLinea;
	@FXML private PieChart graficoPastel;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ventas2016 = FXCollections.observableArrayList();
		ventas2015 = FXCollections.observableArrayList();
		productos = FXCollections.observableArrayList();
		
		serie1 = new XYChart.Series<String, Double>();
		serie1.setName("Ventas 2016");
		serie1.setData(ventas2016);
		
		serie2 = new XYChart.Series<String, Double>();
		serie2.setName("Ventas 2015");
		serie2.setData(ventas2015);
		
		ventas2016.add(new Data<String,Double>("Enero",100d));
		ventas2016.add(new Data<String,Double>("Febrero",500d));
		ventas2016.add(new Data<String,Double>("Marzo",800d));
		ventas2016.add(new Data<String,Double>("Abril",300d));
		ventas2016.add(new Data<String,Double>("Mayo",1000d));
		
		ventas2015.add(new Data<String,Double>("Enero",90d));
		ventas2015.add(new Data<String,Double>("Febrero",300d));
		ventas2015.add(new Data<String,Double>("Marzo",600d));
		ventas2015.add(new Data<String,Double>("Abril",800d));
		ventas2015.add(new Data<String,Double>("Mayo",90d));
		
		productos.add(new PieChart.Data("Desodorante", 30));
		productos.add(new PieChart.Data("Mantequilla", 50));
		productos.add(new PieChart.Data("Zapatos", 20));
		
		graficoLinea.getData().add(serie1);
		graficoLinea.getData().add(serie2);
		graficoPastel.setData(productos);
		
	}
	
}
