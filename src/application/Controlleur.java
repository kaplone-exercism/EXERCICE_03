package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controlleur{
	
	private Rectangle mur_1;
	private Rectangle porte_1;
	
	public Controlleur(){
	}
	
	public Controlleur(int x){
		mur_1 = new Rectangle(20, 600, Color.BLACK);
	}
	
	public Controlleur(Color couleur){
		mur_1 = new Rectangle(20, 60, couleur);
	}

	public void init() {
		
		mur_1 = new Rectangle(100, 50, Color.AQUAMARINE);
	
	}

	public Rectangle getR0() {
		return mur_1;
	}

	public void setR0(Rectangle r0) {
		this.mur_1 = r0;
	}
}
