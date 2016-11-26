package models;

import java.util.List;

import enums.Orientation;
import enums.Sens;
import javafx.scene.shape.Rectangle;

public class Mur extends Rectangle{
	
	private Orientation orientation;
	private int epaisseur;
	private int position;
	private int debut;
	private int fin;
	private List<Porte> portes;
	
	public Mur(Orientation orientation, int epaisseur, int position, int debut, int fin, List<Porte> portes) {
		
		super(orientation == Orientation.HORIZONTAL ? debut : position,
		      orientation == Orientation.HORIZONTAL ? position : debut, 
		      orientation == Orientation.HORIZONTAL ? fin - debut : epaisseur,
		      orientation == Orientation.HORIZONTAL ? epaisseur : fin - debut);
		
		this.orientation = orientation;
		this.epaisseur = epaisseur;
		this.position = position;
		this.debut = debut;
		this.fin = fin;
		this.portes = portes;
	}
	
	public boolean bloque(Sens sens, Rectangle r){
//		switch (sens){
//		case BAS :
//	    case HAUT : if (orientation == Orientation.VERTICAL){
//	    	           return false;
//	                }
//	                else {
//	                   return this.debut - (r.getX() + r.getWidth()) >= 0 
//	                	   || r.getX() - this.fin >= 0; 
//	                }
//	     
//	    case GAUCHE :
//	    case DROITE : if (orientation == Orientation.HORIZONTAL){
// 			           return false;
//						}
//						else {
//						   return this.debut - (r.getY() + r.getHeight()) >= 0
//							   || r.getY() - this.fin >= 0 ; 
//						}
//	    }
        return false;
	}
	
	public boolean estOuvert(Sens sens, Rectangle r){
		
		switch (sens){
		case BAS :
	    case HAUT : if (orientation == Orientation.VERTICAL){
	    	           return false;
	                }
	                else {
	                   return this.debut - (r.getX() + r.getWidth()) >= 0 
	                	   || r.getX() - this.fin >= 0; 
	                }
	     
	    case GAUCHE :
	    case DROITE : if (orientation == Orientation.HORIZONTAL){
 			           return false;
						}
						else {
						   return this.debut - (r.getY() + r.getHeight()) >= 0
							   || r.getY() - this.fin >= 0 ; 
						}
	    }
        return false;
	}
	
	public boolean estEnContact(Sens sens, Rectangle r){
		
		switch (sens){
		    case HAUT : if (orientation == Orientation.VERTICAL){
		    	           return false;
		                }
		                else {
		                   System.out.println(r.getY() - this.position + this.epaisseur);	
		                   return r.getY() - (this.position + this.epaisseur) >= 0 
		                	   && r.getY() - (this.position + this.epaisseur) <= 5; 
		                }
		    case BAS : if (orientation == Orientation.VERTICAL){
 	           			   return false;
             			}
             			else {
             			   return this.position - (r.getY() + r.getHeight()) >= 0 
             				   && this.position - (r.getY() + r.getHeight()) <= 5 ; 
                        }
		    case GAUCHE : if (orientation == Orientation.HORIZONTAL){
 	           			   return false;
             			}
             			else {
             			   return r.getX() - (this.position + this.epaisseur) >= 0 
             				   && r.getX() - (this.position + this.epaisseur) <= 5; 
             			}
		    case DROITE : if (orientation == Orientation.HORIZONTAL){
     			           return false;
  						}
  						else {
  						   return this.position - (r.getX() + r.getWidth()) >= 0
  							   && this.position - (r.getX() + r.getWidth()) <= 5 ; 
  						}
		}
		
		return false;
	}
	
    public boolean estEnTravers(Sens sens){
    	return false;
	}
    
    public Rectangle getRectangle(){
    	return this;
    }

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public int getEpaisseur() {
		return epaisseur;
	}

	public void setEpaisseur(int epaisseur) {
		this.epaisseur = epaisseur;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getDebut() {
		return debut;
	}

	public void setDebut(int debut) {
		this.debut = debut;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	public List<Porte> getPortes() {
		return portes;
	}

	public void setPortes(List<Porte> portes) {
		this.portes = portes;
	}
	
	
		
}
