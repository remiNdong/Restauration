package fr.restauration.authentification;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

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

}
