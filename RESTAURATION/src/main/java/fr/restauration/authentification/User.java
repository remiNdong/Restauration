package fr.restauration.authentification;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import fr.restauration.model.Notation;


@Entity
public class User implements Serializable {
	
	@Id
	private String email;
	
	private String pseudo;
	
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
		
		return "User [email=" + email + ", pseudo=" + pseudo + "]";
	}
	

	 @OneToMany( mappedBy = "user" )
	 private Set<Notation> notations = new HashSet<Notation>();

	    public Set<Notation> getNotations() {
	        return notations;
	    }

	    public void addNotations( Notation n ) {

	        n.setUser( this );
	        notations.add( n );
	    }

}
