package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import enums.Orientation;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.Mur;

public class ControlleurSettings implements Initializable{
	
	String home =  System.getProperty("user.home");
	File settings_file = new File(home, "shapesinthemazes_niveaux.txt");
	
	
	
	public AnchorPane init(AnchorPane root) {
		
        FileReader fr = null;
        root.getChildren().clear();
        ScrollPane sc = new ScrollPane();
        sc.setHbarPolicy(ScrollBarPolicy.NEVER);
        sc.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        
        sc.setPrefHeight(435);
        VBox vb = new VBox(); 
        vb.setPrefSize(580, 435);
        HBox hb = null;
        AnchorPane preview = null;
    	
		try {
			fr = new FileReader(settings_file);

	    	BufferedReader br = new BufferedReader(fr);
	    	
	    	String s = br.readLine();
			
	    	while(s != null){
	    		
	    		if (s.startsWith("#") || s.trim().equals("")){
	    		}
	    		
	    		else if(s.startsWith("[")){
	    			
	    			if (hb != null){
	    				hb.getChildren().add(preview);
	    				vb.getChildren().add(hb);
	    			}
	    			
	    			hb = new HBox();
	    			hb.setPadding(new Insets(20));
	    			preview = new AnchorPane();
	    			preview.setPrefWidth(200);
	    			preview.setPrefHeight(120);
	    			preview.getChildren().add(new Rectangle(200,120, Color.WHITE));
	    			
	    			Label l = new Label(s.split("\\[")[1].split("\\]")[0] + " : ");
	    			hb.getChildren().add(l);
	    						
	    		}
	    		else {
	    			String ligne = s.split("=")[1];
	    			Orientation or = Orientation.valueOf(ligne.split(",")[0].trim());
	    			int epais = Integer.parseInt(ligne.split(",")[1].trim()) / 5;
	    			int dist = Integer.parseInt(ligne.split(",")[2].trim()) / 5;
	    			int debut = Integer.parseInt(ligne.split(",")[3].trim()) / 5;
	    			int fin = Integer.parseInt(ligne.split(",")[4].trim()) / 5;
	    			preview.getChildren().add(new Mur(or, epais, dist, debut, fin, null));
	    			
	    		}
	    		s = br.readLine();
	    	}
	    	
	    	hb.getChildren().add(preview);
			vb.getChildren().add(hb);
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
		sc.setContent(vb);
		root.getChildren().add(sc);
		return root;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}