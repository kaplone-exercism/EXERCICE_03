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
	
	public static int rienNeBloque(Rectangle r, Sens sens, AnchorPane root){
		
		int delta = 0;

		for (Node n : root.getChildren()){
			try {
				Mur m = (Mur) n;
				if (m.estEnContact(sens, r) < 5){
					if (m.estOuvert(sens, r)){
						if(m.bloque(sens, r)){
							return m.estEnContact(sens, r);
						}
						else {
							delta = 5;
						}	
					}
					else {
						return m.estEnContact(sens, r);
					}
				}
				else {
					delta = 5;
				}
			}
			catch (ClassCastException cce){				
			}
		}
		return delta;		
	}
}
