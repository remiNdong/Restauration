package fr.restauration.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


//Annotation Jackson : Les propriétés json non reprises dans notre objet seront ignorées sans générer d'erreur
@JsonIgnoreProperties(ignoreUnknown = true)

/*
 * Fields represente des attributs qui seront recuperer en deserialisant un Restaurant d'un fichier  json
 */

public class Fields  implements Serializable{
	
	private String code;
	private String type;
	private String nom_restaurant;
	private Double tt_0;
	private Double tt_1;
	private String adresse;
	private String ville;
	
	 @JsonProperty("tt")
	    private void unpackNested(Double [] tt) {
	        this.tt_0 = tt[0];
	        this.tt_1 = tt[1];
	    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNom_restaurant() {
		return nom_restaurant;
	}

	public void setNom_restaurant(String nom_restaurant) {
		this.nom_restaurant = nom_restaurant;
	}

	public Double getTt_0() {
		return tt_0;
	}

	public void setTt_0(Double tt_0) {
		this.tt_0 = tt_0;
	}

	public Double getTt_1() {
		return tt_1;
	}

	public void setTt_1(Double tt_1) {
		this.tt_1 = tt_1;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	

}
