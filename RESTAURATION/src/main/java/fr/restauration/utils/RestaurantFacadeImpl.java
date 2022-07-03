package fr.restauration.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.TreeMap;

import fr.restauration.model.Restaurant;

public class RestaurantFacadeImpl extends  AbstractRestaurantFacade{
	
	private final String adresseFichier="static/restaurants.txt";
	
	 public  RestaurantFacadeImpl() throws Exception{
	        setRestaurants(new TreeMap<String,Restaurant>());
	        var client = HttpClient.newBuilder().build();
	        try {
	            var request = HttpRequest
	                            .newBuilder()
	                            .uri(URI.create(URL_RESTAURANTS))
	                            .timeout(Duration.ofSeconds(30))
	                            .build();
	            Long startingTime = System.currentTimeMillis();                
	            var response = client.send(request,HttpResponse.BodyHandlers.ofString()); 
	            System.out.println("durée de collecte URL_RESTAURANTS : "+(System.currentTimeMillis()-startingTime));
	            // on sauvegarde la dernière version au cas où ... 
	            sauvegarder(response.body(), adresseFichier);
	            setParser(new JacksonParserRestaurant(response.body())); 
	            //setParser(new GsonParserStationLastStatusImpl(response.body())); 
	            getParser().setCommand(getAddCommand());
	            startingTime = System.currentTimeMillis();
	            getParser().parse();
	            System.out.println("durée de parsing Jackson: "+(System.currentTimeMillis()-startingTime));
	        } catch (IOException | InterruptedException e) {
	            // on récupère la dernière version sauvegardée en cas d'exception
	            setParser(new JacksonParserRestaurant(restaurer(adresseFichier))); 
	            getParser().setCommand(getAddCommand());
	            getParser().parse();
	        }
	    }
	    

}
