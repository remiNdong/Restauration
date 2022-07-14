package fr.restauration.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.restauration.authentification.User;

@Entity
public class Notation implements Serializable , Comparable<Notation> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "email")
	private User user;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user=user;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "recordid")
	private Restaurant restaurant;
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant=restaurant;
	}
	
	private String commentaire;
	
	public String getCommentaire() {
		return commentaire;
	}
	
	public void setCommentaire(String commentaire) {
		this.commentaire=commentaire;
	}
	
	private Timestamp date;
	public Timestamp getDate() {
		return date;
		
	}
	
	public void setDate(Timestamp date) {
		this.date=date;
	}
	
	private Integer etoiles;
	public Integer getEtoiles() {
		return etoiles;
	}
	
	public void setEtoiles(Integer etoiles) {
		this.etoiles=etoiles;
	}
	
	 @Override
	    public boolean equals( Object obj ) {
	        if ( this == obj ) {
	            return true;
	        }
	        if ( !( obj instanceof Notation ) ) {
	            return false;
	        }
	        Notation autre = (Notation) obj;

	        return this.getId() == autre.getId();
	    }

	    @Override
	    public int compareTo( Notation n ) {
	        return this.id.compareTo(n.getId()) ;
	    }
	    
	    public String toString() {
	    	
	    	return "Date:"+date+" User:"+user.getEmail()+" Restaurant:"+restaurant.getNom_restaurant()+" Etoiles:"+etoiles+"  Commentaire:"+commentaire;
	    }

}
