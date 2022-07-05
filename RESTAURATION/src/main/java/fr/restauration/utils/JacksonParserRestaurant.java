package fr.restauration.utils;

import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.restauration.model.Restaurant;

/*
 * Classe qui definit comment parser des restaurants recuperer dans un fichier json
 */
public class JacksonParserRestaurant implements ParserRestaurantI {
	
	   private String body;
	    private Command cmd;
	    private ObjectMapper objectMapper;
	    
	    public JacksonParserRestaurant() {
	    	this.objectMapper = new ObjectMapper();
	    }
	    
	    public JacksonParserRestaurant(String body) {
	        this.body = body;
	        this.objectMapper = new ObjectMapper();
	    }
	    
	    public  void setCommand(Command cmd){
	        this.cmd = cmd;
	    }

	    public void setBody(String body) {
	        this.body=body;
	    }
	    
	    /*
	     * Classe qui permettra de peupler la Map de la facade  avec les restaurants
	     */
	    
	    public void parse() throws Exception {
	        List<Restaurant> listRestaurants = getRestaurants(body);
	        for(Restaurant restaurant : listRestaurants){           
	            try{
	                cmd.execute(restaurant);
	            }catch(NullPointerException e){
	                throw new NullPointerException("setCommand must be called");
	            }catch(Exception e){
	                throw new Exception(e.getMessage());
	            }
	        }
	    }

	    /*
	     * Methode qui permet de recuperer une liste de Restaurants depuis un fichier Json
	     */
	    private List<Restaurant> getRestaurants(String body) throws Exception {
	    	int indexOfRestaurantArray = body.indexOf("datasetid");
			int indexOfEndArray = body.indexOf("facet_groups");
			body = body.substring(indexOfRestaurantArray - 4, indexOfEndArray - 3);
	        return objectMapper.readValue(body, new TypeReference<List<Restaurant>>(){});
	    }
	    
	    

}
