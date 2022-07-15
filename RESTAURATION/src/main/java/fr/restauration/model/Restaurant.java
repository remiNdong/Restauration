package fr.restauration.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//Annotation Jackson : Les propriétés json non reprises dans notre objet seront ignorées sans générer d'erreur
@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
public class Restaurant implements Serializable{
	
	
	
	@Id
	private String recordid;
	
	private String nom_restaurant;
	private String code;
	private String adresse;
	private String ville;
	
	@JsonProperty("fields")
	private void unpackNested(Fields fields) {
		nom_restaurant = fields.getNom_restaurant();
		code = fields.getCode();
		adresse=fields.getAdresse();
		ville=fields.getVille();
	}
	
	
	
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	
	
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public String getNom_restaurant() {
		return nom_restaurant;
	}



	public void setNom_restaurant(String nom_restaurant) {
		this.nom_restaurant = nom_restaurant;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getVille() {
		return ville;
	}



	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public String toString() {
		return "recordid:"+recordid+" nom_restaurant:"+nom_restaurant+ " code:"+code+" adresse:"+adresse+" ville:"+ville;
	}
	
	 @OneToMany( mappedBy = "restaurant",fetch = FetchType.EAGER )
	    private List<Notation> notations = new ArrayList<Notation>();

	    public List<Notation> getNotations() {
	        return notations;
	    }

	    public List<Notation> getNotationsTriees() {
	        List<Notation> listNotations = new ArrayList<Notation>( notations );
	        Collections.sort( listNotations );
	        return listNotations;
	    }

	    public void addNotations( Notation n ) {

	        n.setRestaurant( this );
	        notations.add( n );
	    }
	    
	    
	    public int getMoyenne() {
	    	
	    	if(notations.size()==0)
	    		return 0;
	    	
	    	int somme=0;
	    	for(Notation n : notations)
	    		somme=somme+n.getEtoiles();
	    	
	    
	    	return somme/notations.size();
	    	
	    }
	    
	    public int getTailleNotations() {
	    	return notations.size();
	    }
	    
	    @Override
	    public boolean equals( Object obj ) {
	        if ( this == obj ) {
	            return true;
	        }
	        if ( !( obj instanceof Restaurant ) ) {
	            return false;
	        }
	        Restaurant autre = (Restaurant) obj;

	        return this.getRecordid() == autre.getRecordid();
	    }

	   
	

}
