package fr.restauration.utils;

import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.restauration.model.Restaurant;

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
	    
	    public void parse() throws Exception {
	        List<Restaurant> listRestaurants = getRestaurants(body);
	        //System.out.println(listStations.get(1));
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

	    private List<Restaurant> getRestaurants(String body) throws Exception {
	    	int indexOfRestaurantArray = body.indexOf("datasetid");
			int indexOfEndArray = body.indexOf("facet_groups");
			body = body.substring(indexOfRestaurantArray - 4, indexOfEndArray - 3);
	        return objectMapper.readValue(body, new TypeReference<List<Restaurant>>(){});
	    }
	    
	    

}
