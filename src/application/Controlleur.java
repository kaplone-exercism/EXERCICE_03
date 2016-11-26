package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controlleur{
	
	private Rectangle perso;

	public Controlleur(){
	}

	public void init() {
		
		perso = new Rectangle(100, 50, Color.AQUAMARINE);
	
	}

	public Rectangle getR0() {
		return perso;
	}

	public void setR0(Rectangle r0) {
		this.perso = r0;
	}
}
