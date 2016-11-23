package application;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Contact {
	
	public static boolean estEnContact(Rectangle r, Rectangle mur){
		
		System.out.println(r.getX());
		System.out.println(mur.getLayoutX());
		
		if (r.getX() + r.getWidth() >= mur.getLayoutX()){
			//r.setX(0); reviens completement a gauche
			r.setX(mur.getLayoutX() - r.getWidth());
			r.setFill(Color.RED);
		}
		

		return false; // ça sert pas a grand chose
	}

}
