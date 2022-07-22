package fr.restauration.authentification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.restauration.model.Favoris;
import fr.restauration.model.Notation;
import fr.restauration.model.Restaurant;

@Entity
@Table(name="user")
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

	@OneToMany(mappedBy = "user")
	private List<Notation> notations = new ArrayList<Notation>();

	public List<Notation> getNotations() {
		return notations;
	}

	public void addNotations(Notation n) {

		n.setUser(this);
		notations.add(n);
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Favoris> favoris = new ArrayList<Favoris>();

	public List<Favoris> getFavoris() {
		return favoris;
	}

	public void addFavoris(Favoris f) {

		f.setUser(this);
		favoris.add(f);
	}

	public Set<Restaurant> restauraurantFavoris() {

		Set<Restaurant> restauraurantFavoris = new HashSet<Restaurant>();

		for (Favoris f : favoris)
			restauraurantFavoris.add(f.getRestaurant());

		return restauraurantFavoris;
	}

}
