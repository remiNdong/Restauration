package fr.restauration.outils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.restauration.model.Restaurant;

class EnsemblePageTest {

	@Test
	void testCreation() {

		Restaurant r1 = new Restaurant();
		r1.setRecordid("A");
		Restaurant r2 = new Restaurant();
		r2.setRecordid("B");
		Restaurant r3 = new Restaurant();
		r3.setRecordid("C");
		List<Restaurant> listResto = new ArrayList<Restaurant>();
		listResto.add(r1);
		listResto.add(r2);
		listResto.add(r3);
		EnsemblePage<Restaurant> ensemblePage = new EnsemblePage<Restaurant>(listResto);
		int nbList = ensemblePage.getPages().keySet().size();
		assertEquals(1, nbList);

		List<Restaurant> listBis = ensemblePage.getPage(1);
		assertEquals(3, listBis.size());
		Restaurant r4 = listBis.get(0);
		Restaurant r5 = listBis.get(1);
		Restaurant r6 = listBis.get(2);
		assertEquals(r1, r4);
		assertEquals(r2, r5);
		assertEquals(r3, r6);

	}

	@Test
	void testnbPages() {

		List<Restaurant> listResto = new ArrayList<Restaurant>();

		for (int i = 0; i < 35; i++) {
			listResto.add(new Restaurant());
			listResto.get(i).setRecordid(i + "Resto");

		}
		EnsemblePage<Restaurant> ensemblePage = new EnsemblePage<Restaurant>(listResto);
		int nbList = ensemblePage.getPages().keySet().size();
		// il doit y avoir 4 pages
		assertEquals(4, nbList);
		List<Restaurant> page4 = ensemblePage.getPage(4);
		List<Restaurant> page3 = ensemblePage.getPage(3);
		// il doit rester seulement 5 service a la page 4
		assertEquals(5, page4.size());
		assertEquals("34Resto", page4.get(4).getRecordid());
		assertEquals("24Resto", page3.get(4).getRecordid());
	}

	@Test
	void testnul() {
		
		List<Restaurant> listResto = new ArrayList<Restaurant>();
		EnsemblePage<Restaurant> ensemblePage = new EnsemblePage<Restaurant>(listResto);
		int nbList = ensemblePage.getPages().keySet().size();
		// il doit y avoir 4 pages
		assertEquals(0, nbList);
		assertEquals(0,ensemblePage.taille());

	}

}
