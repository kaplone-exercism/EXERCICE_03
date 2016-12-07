package application;

import javafx.scene.shape.Rectangle;
import models.Mur;
import enums.Sens;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Contact {
	
	private static double ecart = 0;
	
	public static int rienNeBloque(Rectangle r, Sens sens, AnchorPane root){
		
		return root.getChildren().stream()
								 .filter(a -> a instanceof Mur)
								 .mapToInt(a -> ((Mur) a).estEnContact(sens, r))
								 .min()
								 .getAsInt();	
	
	}
	
    public static double rienNeBloque(Rectangle r, Sens sens, AnchorPane root, Double delta){
    	
    	
    	Rectangle r_t = rect_temporaire(r, sens, delta);
    	System.out.println(r_t.toString());
    	System.out.println("ecart : " + ecart);
    	
    	
    	root.getChildren().stream()
         		 .filter(a -> a instanceof Mur)
         		 .mapToDouble(a -> ((Mur) a).estEnContact(sens, r_t, ecart)) // l'ecart c'est la progression des deux cotés qui s'allongent
         		 .forEach(a -> System.out.println(a));                       // estEnContact doit retourner une valeur acceptable pour le coté qui se racourcis
    	
    	return root.getChildren().stream()
    	                  		 .filter(a -> a instanceof Mur) 
    	                  		 .mapToDouble(a -> ((Mur) a).estEnContact(sens, r_t, ecart))
    	                  		 .min()
    	                  		 .getAsDouble();	
	}
    
    public static Rectangle rect_temporaire(Rectangle r, Sens sens, Double delta){
    	
        Rectangle r_temp = new Rectangle();
        double surface = r.computeAreaInScreen();
        double newSize;
    	
    	if (sens == Sens.HAUT || sens == Sens.BAS){

    		r_temp.setHeight(r.getHeight() + delta);
    		
    		newSize = surface / r_temp.getHeight();
    		ecart = (r.getWidth() - newSize) / 2;
    		
        	r_temp.setX(r.getX() + ecart);
    		r_temp.setWidth(newSize);
    	}
    	else {
    		r_temp.setWidth(r.getWidth() + delta);
    		
    		newSize = surface / r_temp.getWidth();
    		ecart = (r.getHeight() - newSize) / 2;
    		
        	r_temp.setY(r.getY() + ecart);
    		r_temp.setHeight(newSize);
    	}
    	return r_temp;
    }
}
