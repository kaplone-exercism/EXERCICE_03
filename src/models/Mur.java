package models;

import java.util.List;

import enums.Orientation;
import enums.Sens;
import javafx.scene.paint.Color;
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
		this.setFill(Settings.getCouleurMurs());
		
	}
	
	public boolean estOuvert(Sens sens, Rectangle r){
		
		switch (sens){
		case BAS :  
	    case HAUT : if (orientation == Orientation.VERTICAL){
	    				return !(r.getX() > this.position - r.getWidth()
	    				      && r.getX() < this.position + this.epaisseur);
	                }
	                else {
	                   return this.debut - (r.getX() + r.getWidth()) >= 0 
	                	                 || r.getX() - this.fin >= 0; 
	                }
	     
	    case GAUCHE :
	    case DROITE : if (orientation == Orientation.HORIZONTAL){
 			           return !(r.getY() > this.position - r.getHeight()
 			                 && r.getY() < this.position + this.epaisseur);
						}
						else {
						   return this.debut - (r.getY() + r.getHeight()) >= 0
							                 || r.getY() - this.fin >= 0 ; 
						}
	    }
        return false;
	}
	
	public int estEnContact(Sens sens, Rectangle r){
		
		switch (sens){
		    case HAUT : if (orientation == Orientation.VERTICAL){
                			if(r.getY() - this.fin >= 0 
                			&& r.getY() - this.fin < 5
                			&& ! this.estOuvert(sens, r)){
             	               return (int) (r.getY() - this.fin);
                            }
                            else return 5;
		                }
		                else {
		                   if(r.getY() - (this.position + this.epaisseur) >= 0 
		                   && r.getY() - (this.position + this.epaisseur) < 5
		                   && ! this.estOuvert(sens, r)){ // modifier le sens ou créer un estOuvert adapté
		                	   return (int) (r.getY() - (this.position + this.epaisseur));
		                   }
		                   else return 5; 
		                }
		    
		    case BAS : if (orientation == Orientation.VERTICAL){
		    			  if(this.debut - (r.getY() + r.getHeight()) >= 0
	    				  && this.debut - (r.getY() + r.getHeight()) <= 5
	    				  && ! this.estOuvert(sens, r)){
						     return  (int) (this.debut - (r.getY() + r.getHeight())); 
					   }
					   else return 5;
             			}
             			else {
             			   if(this.position - (r.getY() + r.getHeight()) >= 0 
             			   && this.position - (r.getY() + r.getHeight()) <= 5
             			   && ! this.estOuvert(sens, r)){
             				   return (int) (this.position - (r.getY() + r.getHeight()));
             			   }
             			   else return 5;
                        }
		    case GAUCHE : if (orientation == Orientation.HORIZONTAL){
		    				if(r.getX() - this.fin >= 0
		    				&& r.getX() - this.fin <= 5
		    				&& ! this.estOuvert(sens, r)){
		    					return  (int) (r.getX() - this.fin); 
					   }
					   else return 5;
             			}
             			else {
             			   if(r.getX() - (this.position + this.epaisseur) >= 0 
             				   && r.getX() - (this.position + this.epaisseur) <= 5
             				   && ! this.estOuvert(sens, r)){
             				   return (int) (r.getX() - (this.position + this.epaisseur));
             			   } 
             			   else return 5;
             			}
		    case DROITE : if (orientation == Orientation.HORIZONTAL){
		    				if(this.debut - (r.getX() + r.getWidth()) >= 0
		    				&& this.debut - (r.getX() + r.getWidth()) <= 5
		    				&& ! this.estOuvert(sens, r)){
							   return  (int) (this.debut - (r.getX() + r.getWidth())); 
						   }
						   else return 5;
  						}
  						else {
  						   if(this.position - (r.getX() + r.getWidth()) >= 0
  						   && this.position - (r.getX() + r.getWidth()) <= 5
  						   && ! this.estOuvert(sens, r)){
  							   return  (int) (this.position - (r.getX() + r.getWidth())); 
  						   }
  						   else return 5;
  						}
		}
		return 5;
	}
	
    public double estEnContact(Sens sens, Rectangle r, double max){
		
		switch (sens){
		    case DROITE :
		    case GAUCHE : if (orientation == Orientation.VERTICAL){
                			if(r.getY() - this.fin >= 0 
                			&& r.getY() - this.fin < - max
                			&& ! this.estOuvert(Sens.HAUT, r)){ // revoir estOuvert ?
             	               return r.getY() - this.fin; // autre valeur : une diminution acceptable
                            }
                			else if(this.debut - (r.getY() + r.getHeight()) >= 0
          	    				  && this.debut - (r.getY() + r.getHeight()) <= - max
          	    				  && ! this.estOuvert(Sens.HAUT, r)){
          						     return  this.debut - (r.getY() + r.getHeight()); // autre valeur : une diminution acceptable
          					}
                            else return max; // autre valeur : la diminution maximum (0.5)
		                }
		                else {
		                   if(r.getY() - (this.position + this.epaisseur) >= 0 
		                   && r.getY() - (this.position + this.epaisseur) < - max
		                   && ! this.estOuvert(Sens.HAUT, r)){
		                	   return r.getY() - (this.position + this.epaisseur);
		                   }
		                   else if(this.position - (r.getY() + r.getHeight()) >= 0 
		             			   && this.position - (r.getY() + r.getHeight()) <= - max
		             			  && ! this.estOuvert(Sens.HAUT, r)){
		             				   return this.position - (r.getY() + r.getHeight());
		             			   }
		                   else return max; 
		                }
		    case HAUT :
		    case BAS : if (orientation == Orientation.HORIZONTAL){
		    				if(r.getX() - this.fin >= 0
		    				&& r.getX() - this.fin <= - max
		    				&& ! this.estOuvert(Sens.DROITE, r)){
		    					return  r.getX() - this.fin; 
					   }
		    				else if(this.debut - (r.getX() + r.getWidth()) >= 0
				    				&& this.debut - (r.getX() + r.getWidth()) <= - max
				    				&& ! this.estOuvert(Sens.DROITE, r)){
									   return  this.debut - (r.getX() + r.getWidth()); 
								   }
					        else return max;
             			}
             			else {
             			   if(r.getX() - (this.position + this.epaisseur) >= 0 
             				   && r.getX() - (this.position + this.epaisseur) <= - max
             				  && ! this.estOuvert(Sens.DROITE, r)){
             				   return r.getX() - (this.position + this.epaisseur);
             			   } 
             			   else if(this.position - (r.getX() + r.getWidth()) >= 0
         						   && this.position - (r.getX() + r.getWidth()) <= - max
         						 && ! this.estOuvert(Sens.DROITE, r)){
         							   return  this.position - (r.getX() + r.getWidth()); 
         						   }
             			   else return max;
             			}
		}
		return max;
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
