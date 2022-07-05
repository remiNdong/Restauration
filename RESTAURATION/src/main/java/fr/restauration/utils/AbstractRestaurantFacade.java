package fr.restauration.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import fr.restauration.model.Restaurant;

//SEULS LES CONSTRUCTEURS SONT LAISSES A LA CHARGE DES IMPLEMENTATIONS
public abstract class AbstractRestaurantFacade implements RestaurantFacadeI, Iterable<Restaurant>{

	    private Map<String,Restaurant> restaurants;
	    private ParserRestaurantI parser;
	    private ParserRestaurantI.Command addCommand = new ParserRestaurantI.Command(){
	            public void execute(Restaurant r){
	            	AbstractRestaurantFacade.this.restaurants.put(r.getRecordid(), r);
	            }
	        };

	    public ParserRestaurantI.Command getAddCommand() {
	        return addCommand;
	    }
	    
	    public void setRestaurants(Map<String,Restaurant> restaurants){
	        this.restaurants=restaurants;
	    }
	    

	    @Override
	    public void setParser(ParserRestaurantI parser){
	        this.parser = parser;
	    }

	    @Override
	    public ParserRestaurantI getParser(){
	        return this.parser;
	    }

	    @Override
	    public Restaurant getRestaurant(String idRestaurant) { 
	        return restaurants.get(idRestaurant);
	    }
	    

	    @Override
	    public Collection<Restaurant> getListeRestaurants() {
	        return Collections.unmodifiableCollection(restaurants.values());
	    }


	    public Iterator<Restaurant> iterator(){
	        return new Iterator<Restaurant>(){
	            Iterator<Restaurant> it = restaurants.values().iterator();
	            public boolean hasNext(){return it.hasNext();}

	            public Restaurant next(){ return it.next();}

	            public void remove(){throw new RuntimeException();}
	        };
	    }
	    

	    // Sauvegarder la réponse du serveur dans un fichier au cas ou il y a un probleme a la prochaine requete Http
	    //pour recuperer le json
	    public static void sauvegarder(String response, String nomDuFichier)throws Exception{
	        try{
	            OutputStream out = new FileOutputStream(new File(nomDuFichier));
	            InputStream targetStream = new ByteArrayInputStream(response.getBytes());
	            byte[] buf = new byte[1024];
	            int len;
	            while ((len = targetStream.read(buf)) > 0){
	                out.write(buf, 0, len);
	            }
	            targetStream.close();
	            out.close();
	            System.out.println(nomDuFichier+" file copied.");
	        }
	        catch(IOException e){
	            e.printStackTrace();
	        }
	    }
	    
	    //methode qui sera utilisee au cas ou a un probleme a la prochaine requete Http pour recuperer le json
	    public static String restaurer(String nomDuFichier)throws IOException{
	        Path fileName = Path.of(nomDuFichier);
	        return Files.readString(fileName);
	    }

}
