package fr.restauration.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.restauration.model.Restaurant;

class RestaurantFacadeImplTest {

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

}
