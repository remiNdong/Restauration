package fr.restauration.utils;

import java.util.Collection;

import fr.restauration.model.Restaurant;



public interface RestaurantFacadeI {
	
	public static final String URL_RESTAURANTS="https://opendata.paris.fr/api/records/1.0/search/?dataset=restaurants-casvp&q=&facet=code&facet=nom_restaurant&facet=type.json";
	
	public Collection<Restaurant> getListeRestaurants();
	
	public Restaurant getRestaurant(String idRestaurant);
	
	  /** Obtention du parser json associé.
     * @return le parser json choisi
     */
    public ParserRestaurantI getParser();

    /** Affectation, sélection du parser json.
     */
    public void setParser(ParserRestaurantI parser);
	
	

}
