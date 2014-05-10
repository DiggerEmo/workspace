package de.ulm.uni.vs.avid.roary;

import java.util.List;
import java.util.Vector;

public class RoarHistory {
	private List<Roar> roars_;
	
	public RoarHistory(){
		roars_ = new Vector<Roar>();
		String auth = "Steffen";
		String[] texts = {
				"Bruell!",
				"Grunz!",
				"Tob!"
		}; 
		for( String text : texts ){
			Roar r = new Roar();
			r.setAuthor(auth);
			r.setText(text);
			roars_.add(r);
	    }
 	}
	
	public void addRoar(Roar r){
		roars_.add(r);
	}
	
	public void addRoar(String auth, String txt){
		Roar r = new Roar();
		r.setAuthor(auth);
		r.setText(txt);
		addRoar(r);
	}
	
	public List<Roar> getRoars(){
		return roars_;
	}
	
	public Roar getRoar(int id){
		Roar result = null;
		for( Roar r: roars_ ) if( id == r.getId()) result = r ;
		return result;
	}
}
