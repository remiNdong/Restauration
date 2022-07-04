package fr.restauration.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.restauration.model.Restaurant;

class RestaurantFacadeImplTest {
	
	/*Test du constructeur par defaut
	 * recuperation du fichier Json via une requete Http
	 * creation de la Map des Restaurants 
	 */

	@Test
	void test() {
		try {
			RestaurantFacadeI facade=new RestaurantFacadeImpl();
			assertTrue(facade.getListeRestaurants().size()>0);
			assertEquals(facade.getRestaurant("257af888dc2719b01850e5f6752bbfc0c71d3ede").getNom_restaurant(),"CLIGNANCOURT");
			
			for(Restaurant resto : facade.getListeRestaurants())
				System.out.println(resto);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*Test au cas ou  constructeur par defaut echoue
	 * recuperation du fichier Json sur le disque
	 * creation de la Map des Restaurants 
	 */
	
	@Test
	void test2() {
		try {
			RestaurantFacadeImpl facade=new RestaurantFacadeImpl();
			facade.setParser(new JacksonParserRestaurant(facade.restaurer("restaurants.txt"))); 
			facade.getParser().setCommand(facade.getAddCommand());
			facade.getParser().parse();
			assertTrue(facade.getListeRestaurants().size()>0);
			assertEquals(facade.getRestaurant("257af888dc2719b01850e5f6752bbfc0c71d3ede").getNom_restaurant(),"CLIGNANCOURT");
			
			for(Restaurant resto : facade.getListeRestaurants())
				System.out.println(resto);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
