package application;

import javafx.scene.shape.Rectangle;
import models.Mur;
import enums.Sens;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
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
	
	public static boolean rienNeBloque(Rectangle r, Sens sens, AnchorPane root){
		
		
		
		for (Node n : root.getChildren()){
			try {
				Mur m = (Mur) n;
				if (m.estEnContact(sens, r)){
					if (m.estOuvert(sens, r)){
						if(m.bloque(sens, r)){
							return false;
						}
						return true;
					}
					return false;
				}
			}
			catch (ClassCastException cce){				
			}
		}
		
		return true;
	}

}
