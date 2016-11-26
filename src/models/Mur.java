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
	
	public boolean bloque(Sens sens, int coordonnees){
        return false;
	}
	
	public boolean estOuvert(Sens sens, int coordonnees){
        return false;
	}
	
	public boolean estEnContact(Sens sens, int coordonnees){
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
